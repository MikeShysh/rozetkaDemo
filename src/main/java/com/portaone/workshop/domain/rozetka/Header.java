package com.portaone.workshop.domain.rozetka;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class Header {
	public SelenideElement getHeaderCartItems() {
		return $("#cart_popup_header .hub-i-count");
	}
	public void scrollToHeader() {
		$(".logo").scrollTo();
	}
}
