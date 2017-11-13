package com.portaone.workshop.google;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;

public class SearchResults {

	public void openSite(String site) {
		$(withText(site)).click();
	}
}
