package com.portaone.workshop;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Condition.attribute;

public class SearchResults {
    public static List<Dron> getListOfProducts(ElementsCollection elements) {
        List<Dron> drons = new ArrayList<>();
        for (SelenideElement elt: elements) {
            String name = elt.$(".g-i-tile-i-title a").getText();
            int price = Integer.parseInt(elt.$("[name=\"price\"] .g-price-uah").getText()
                    .trim().replaceAll("\\D+",""));
            int rating = 0;
            if (!elt.$(".g-rating .g-rating-none").exists())
                rating = Integer.parseInt(elt.$(".g-rating .g-rating-stars-i")
                        .getAttribute("style").replaceAll("\\D+",""));
            int reviews = 0;
            if (elt.$(".g-rating a").has(attribute("data-count")))
                reviews = Integer.parseInt(elt.$(".g-rating a").getAttribute("data-count"));
            boolean isAvailable = !elt.$(".g-i-status.unavailable").exists();

            drons.add(new Dron(name, price, reviews, rating, isAvailable));
        }
        System.out.println(drons);
        return drons;
    }
}
