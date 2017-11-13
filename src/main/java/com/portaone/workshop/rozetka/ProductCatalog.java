package com.portaone.workshop.rozetka;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.portaone.workshop.models.Dron;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ProductCatalog {

    public SelenideElement getCatalogTitle() {
        return $("#catalog_title_block [itemprop=\"name\"]");
    }

    public SelenideElement getEmptyBlock() {
        return $("#empty_block");
    }

    public ProductCatalog sortBy(String opt) {
        $("[name=\"drop_link\"]").click();
        $("[name=\"drop_menu\"]")
                .shouldHave(attribute("style",
                        "visibility: visible; zoom: 1; opacity: 1;"));
        $("[name=\"drop_menu\"]").$(byText(opt)).click();
        $(".progress-b").shouldHave(cssClass("hidden"));
        return this;
    }

    public ProductDetails openProduct(String name) {
        $("[name=\"goods_list\"]").$(byText(name)).click();
        return new ProductDetails();
    }

    public List<Dron> getListOfProducts() {
        List<Dron> drons = new ArrayList<>();
        ElementsCollection elts = $$("[name=\"goods_list\"] [data-view_type=\"catalog_with_hover\"]");
        for (SelenideElement elt: elts) {

            String productItemHtml = elt.innerHtml();
            Element div = new Element(Tag.valueOf("div"),"");
            div.html(productItemHtml);

            String name = div.select(".g-i-tile-i-title a").first().text();
            int price = Integer.parseInt(div.select("[name=\"price\"] .g-price-uah").first().text()
                    .trim().replaceAll("\\D+",""));
            int rating = 0;
            if (div.select(".g-rating .g-rating-none").isEmpty())
                rating = Integer.parseInt(div.select(".g-rating .g-rating-stars-i").first()
                        .attr("style").replaceAll("\\D+",""));
            int reviews = 0;
            if (div.select(".g-rating a").first().hasAttr("data-count"))
                reviews = Integer.parseInt(div.select(".g-rating a").first().attr("data-count"));
            boolean isAvailable = div.select(".g-i-status.unavailable").isEmpty();

            drons.add(new Dron(name, price, reviews, rating, isAvailable));
        }
        return drons;
    }
}
