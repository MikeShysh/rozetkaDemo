package com.portaone.workshop.domain.rozetka;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

public class FilterBar {

	public FilterBar checkOptionsFromSection(String section, String... options) {
		SelenideElement sectionEl = $(".sidebar")
				.$(withText(section))
				.closest(".sidebar-block");
		for (String opt: options) {
			sectionEl.scrollIntoView(true);
			sectionEl.$(withText(opt)).closest("a").click();
			$(".preloader_type_goods").shouldNotBe(visible);
		}
		return this;
	}

	public FilterBar setPriceMax(String maxPrice) {
		SelenideElement el = $(".sidebar-block[data-filter-name=price]");
		el.$("input[formcontrolname=\"max\"]").setValue(maxPrice);
		el.$("button[type=\"submit\"]").click();
		$(".preloader_type_goods").shouldNotBe(visible);
		return this;
	}

	public FilterBar searchAndSetProducer(String name) {
		SelenideElement el = $(".sidebar-block[data-filter-name=\"producer\"]");
		el.$("input[name=\"searchline\"]").setValue(name);
		el.$(withText(name)).closest("a").click();
		$(".preloader_type_goods").shouldNotBe(visible);
		return this;
	}
}
