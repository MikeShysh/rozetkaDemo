package com.portaone.workshop.domain.rozetka;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class CartPopUp {
	public SelenideElement getCartHeader() {
		return  $("#cart-popup .cart-title");
	}
	public int getCartTotalAmount() {
		return Integer.parseInt($(".cart-popup .purchase-sum-coast")
				.getText().replaceAll("\\D", ""));
	}
	public void close() {
		$("a.rz-popup-close").click();
	}
}
