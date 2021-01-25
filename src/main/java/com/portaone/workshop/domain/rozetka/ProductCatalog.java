package com.portaone.workshop.domain.rozetka;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.portaone.workshop.domain.models.Dron;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ProductCatalog {

    public SelenideElement getCatalogTitle() {
        return $(".catalog-heading");
    }

    public SelenideElement getEmptyBlock() {
        return $("#empty_block");
    }

    public ProductCatalog sortBy(String opt) {
        $(".catalog-settings__sorting").click();
        $(".catalog-settings__sorting select").selectOption(opt);
        $(".preloader_type_goods").shouldNotBe(visible);
        return this;
    }

    public ProductDetails openProduct(String name) {
        $("a[title='" + name + "']").click();
        $(".header__logo").hover();
        return new ProductDetails();
    }

    public List<Dron> getListOfProducts() {
        List<Dron> drons = new ArrayList<>();
        ElementsCollection elts = $$(".catalog-grid .goods-tile");
        for (SelenideElement elt: elts) {

            String productItemHtml = elt.innerHtml();
            Element div = new Element(Tag.valueOf("div"),"");
            div.html(productItemHtml);

            String name = div.select(".goods-tile__heading").attr("title");
            int price = Integer.parseInt(div.select(".goods-tile__price-value").first().text()
                    .trim().replaceAll("\\D+",""));
            int rating = 0;
            if (!div.select(".goods-tile__stars linearGradient > stop").isEmpty())
                rating = Integer.parseInt(div.select(".goods-tile__stars linearGradient > stop").first()
                        .attr("offset").replaceAll("\\D+",""));
            int reviews = 0;
            if (!div.select(".goods-tile__reviews-link").hasAttr("href"))
                reviews = Integer.parseInt(div.select(".goods-tile__reviews-link").text().replaceAll("\\D+",""));
            boolean isAvailable = div.select(".goods-tile__availability.goods-tile__availability_type_unavailable").isEmpty();

            drons.add(new Dron(name, price, reviews, rating, isAvailable));
        }
        return drons;
    }
}
