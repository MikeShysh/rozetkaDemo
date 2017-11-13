package com.portaone.workshop.domain.google;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$;

public class Google {
	public SearchResults search(String searchPhrase) {
		$(By.name("q")).sendKeys(searchPhrase);
		$(By.name("q")).sendKeys(Keys.ENTER);
		return new SearchResults();
	}
}
