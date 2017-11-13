package com.portaone.workshop.rozetka;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ProductDetails {

	public SelenideElement getTitle() {
		return $("h1[itemprop=\"name\"]");
	}

	public SelenideElement getDeliveryOptions(String section) {
		return $$("[name=\"delivery_div\"] .detail-dwp-subi").find(text(section));
	}

	public void buy() {
		$("[name=\"topurchases\"]").click();
	}
}
