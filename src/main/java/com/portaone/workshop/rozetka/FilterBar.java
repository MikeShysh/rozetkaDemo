package com.portaone.workshop.rozetka;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;

public class FilterBar {

	public FilterBar checkOptionsFromSection(String section, String... options) {
		SelenideElement sectionEl = $(".filter")
				.$(withText(section))
				.closest(".filter-parametrs-i");
		for (String opt: options) {
			sectionEl.$(withText(opt)).closest("a").click();
			$(".progress-b").shouldHave(cssClass("hidden"));
		}
		return this;
	}

	public FilterBar setPriceMax(String maxPrice) {
		$(".filter [name=\"price[max]\"]").sendKeys(maxPrice);
		$(".filter #submitprice").click();
		$(".progress-b").shouldHave(cssClass("hidden"));
		return this;
	}
}
