package com.portaone.workshop.domain.google;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class SearchResults {

	public void openSite(String site) {
		$$("#search .g").find(text(site)).$("a").click();
	}

	public SearchResults scrollDown(int shiftStep) {
		executeJavaScript("window.scrollBy(0, " + shiftStep + ");");
		return new SearchResults();
	}
}
