package com.portaone.workshop.domain.rozetka;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ProductDetails {

	public SelenideElement getTitle() {
		return $("h1.product__title");
	}

	public ElementsCollection getDeliveryOptions() {
		return $$(".product-delivery .product-delivery__item");
	}

	public void buy() {
		$("button.buy-button").click();
	}
}
