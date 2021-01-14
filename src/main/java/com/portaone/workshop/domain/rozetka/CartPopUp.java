package com.portaone.workshop.domain.rozetka;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class CartPopUp {
	public int getCartTotalAmount() {
		return Integer.parseInt($(".cart-receipt .cart-receipt__sum-price")
				.getText().replaceAll("\\D", ""));
	}
	public void close() {
		$("button.modal__close").click();
	}
}
