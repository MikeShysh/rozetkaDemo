package com.portaone.workshop.rozetka;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class CartPopUp {
	public SelenideElement getCartHeader() {
		return  $("#cart-popup .cart-title");
	}
	public int getCartTotalAmount() {
		return Integer.parseInt($("#cart-popup [name=\"cost\"]")
				.getText().replace(" ",""));
	}
	public void close() {
		$("#cart-popup [name=\"close\"]").click();
	}
}
