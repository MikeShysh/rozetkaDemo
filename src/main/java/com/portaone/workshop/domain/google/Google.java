package com.portaone.workshop.domain.google;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class Google {
	public SearchResults search(String searchPhrase) {
		$(By.name("q")).sendKeys(searchPhrase);
		$(By.name("q")).sendKeys(Keys.ENTER);
		return new SearchResults();
	}

	public SearchResults scrollDown(int shiftStep) {
		executeJavaScript("arguments[0].scrollBy(0, " + shiftStep + ");", $("#cnt"));
		return new SearchResults();
	}
}
