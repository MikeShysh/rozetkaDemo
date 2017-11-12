package com.portaone.workshop;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.portaone.workshop.SearchResults.getListOfProducts;

public class BuyDron {

    @Test
    public void buyDron() {
        open("/");
        $(By.name("q")).sendKeys("квадрокоптер");
        $(By.name("q")).sendKeys(Keys.ENTER);
        $(withText("Rozetka")).click();
        $("#catalog_title_block [itemprop=\"name\"]").shouldHave(text("Квадрокоптеры"));
        $(".filter .filter-parametrs-i-header")
                .$(withText("Камера"))
                .closest(".filter-parametrs-i")
                .$(withText("С камерой")).closest("a").click();
        $(".progress-b").shouldHave(cssClass("hidden"));
        $(".filter")
                .$(withText("Функция возврата на точку взлета"))
                .closest(".filter-parametrs-i")
                .$(withText("С функцией")).closest("a").click();
        $(".progress-b").shouldHave(cssClass("hidden"));
        $(".filter")
                .$(withText("Автопилот"))
                .closest(".filter-parametrs-i")
                .$(withText("Без автопилота")).closest("a").click();
        $(".progress-b").shouldHave(cssClass("hidden"));
        $(".filter [name=\"price[max]\"]").sendKeys("12000");
        $(".filter #submitprice").click();
        $(".progress-b").shouldHave(cssClass("hidden"));
        $(".filter")
                .$(withText("Функция FPV"))
                .closest(".filter-parametrs-i")
                .$(withText("С функцией")).closest("a").click();
        $(".progress-b").shouldHave(cssClass("hidden"));
        $(".filter")
                .$(withText("Производитель"))
                .closest(".filter-parametrs-i")
                .$(withText("Kingco")).closest("a").click();
        $(".progress-b").shouldHave(cssClass("hidden"));
        $(".filter")
                .$(withText("Производитель"))
                .closest(".filter-parametrs-i")
                .$(withText("Skytech")).closest("a").click();
        $("#empty_block").shouldNot(exist);
        $(".logo").scrollTo();
        $("[name=\"drop_link\"]").click();
        $("[name=\"drop_menu\"]")
                .shouldHave(attribute("style",
                        "visibility: visible; zoom: 1; opacity: 1;"));
        $("[name=\"drop_menu\"]").$(byText("популярные")).click();


        System.out.println(LocalDateTime.now());
        List<Dron> drons = getListOfProducts($$("[name=\"goods_list\"] [data-view_type=\"catalog_with_hover\"]"));
        System.out.println(LocalDateTime.now());
        System.out.println(drons);
        Dron bestDron = drons.stream()
                .filter(dron -> dron.isAvailable() && dron.getRating() > 75 && dron.getReviews() > 5)
                .sorted(Comparator.comparingInt(Dron::getPrice)
                        .thenComparingInt(Dron::getRating)
                        .thenComparingInt(Dron::getReviews))
                .findFirst().orElseThrow(() -> new RuntimeException("Desired dron was not found"));
        System.out.println(LocalDateTime.now());
        $("[name=\"goods_list\"]").$(byText(bestDron.getName())).click();
        $("h1[itemprop=\"name\"]").shouldHave(text(bestDron.getName()));
        $$("[name=\"delivery_div\"] .detail-dwp-subi").find(text("Курьер"))
                .shouldBe(text("Новая почта"));
        $("[name=\"topurchases\"]").click();
        $("#cart-popup .cart-title").shouldBe(visible).shouldHave(text("Корзина"));
        Assert.assertEquals(
                Integer.parseInt($("#cart-popup [name=\"cost\"]")
                        .getText().replace(" ","")),
                bestDron.getPrice());
        $("#cart-popup [name=\"close\"]").click();
        $("#cart_popup_header .hub-i-count").shouldHave(text("1"));



    }
}
