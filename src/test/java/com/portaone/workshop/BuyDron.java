package com.portaone.workshop;

import com.portaone.workshop.google.Google;
import com.portaone.workshop.models.Dron;
import com.portaone.workshop.rozetka.*;;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class BuyDron {

    @Test
    public void buyDron() {
        open("/");
        new Google()
                .search("квадрокоптеры")
                .openSite("Rozetka");

        ProductCatalog catalog = new ProductCatalog();
        catalog.getCatalogTitle().shouldHave(text("Квадрокоптеры"));

        new FilterBar()
                .checkOptionsFromSection("Камера","С камерой")
                .checkOptionsFromSection("Функция возврата на точку взлета","С функцией")
                .checkOptionsFromSection("Автопилот","Без автопилота")
                .setPriceMax("12000")
                .checkOptionsFromSection("Производитель","Kingco","Skytech");

        catalog.getEmptyBlock().shouldNot(exist);
        new Header().scrollToHeader();
        catalog.sortBy("популярные");

        List<Dron> drons = catalog.getListOfProducts();

        Dron bestDron = drons.stream()
                .filter(dron -> dron.isAvailable() && dron.getRating() > 75 && dron.getReviews() > 5)
                .sorted(Comparator.comparingInt(Dron::getPrice)
                        .thenComparingInt(Dron::getRating)
                        .thenComparingInt(Dron::getReviews))
                .findFirst().orElseThrow(() -> new RuntimeException("Desired dron was not found"));

        catalog.openProduct(bestDron.getName());

        ProductDetails product = new ProductDetails();
        product.getTitle().shouldHave(text(bestDron.getName()));
        product.getDeliveryOptions("Курьер").shouldHave(text("Новая почта"));
        product.buy();

        CartPopUp dronCart = new CartPopUp();
        dronCart.getCartHeader().shouldBe(visible).shouldHave(text("Вы добавили товар в корзину"));
        Assert.assertEquals(dronCart.getCartTotalAmount(), bestDron.getPrice());
        dronCart.close();

        new Header().getHeaderCartItems().shouldHave(text("1"));
    }
}
