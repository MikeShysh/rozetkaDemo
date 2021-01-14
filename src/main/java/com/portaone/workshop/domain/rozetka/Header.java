package com.portaone.workshop.domain.rozetka;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class Header {
	public SelenideElement getHeaderCartItems() {
		return $(".header-actions__item_type_cart .header-actions__button-counter");
	}
	public void scrollToHeader() {
		$(".header").scrollTo();
	}
}
