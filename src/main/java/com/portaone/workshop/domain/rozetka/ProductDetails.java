package com.portaone.workshop.domain.rozetka;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ProductDetails {

	public SelenideElement getTitle() {
		return $("h1[itemprop=\"name\"]");
	}

	public ElementsCollection getDeliveryOptions() {
		return $$("pp-delivery .detail-dwp-l-i");
	}

	public void buy() {
		$(".toOrder button").click();
	}
}
