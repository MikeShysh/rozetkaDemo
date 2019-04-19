package com.portaone.workshop.domain.rozetka;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class Header {
	public SelenideElement getHeaderCartItems() {
		return $("rz-user-buttons-cart .header-actions__button-counter");
	}
	public void scrollToHeader() {
		$(".logo").scrollTo();
	}
}
