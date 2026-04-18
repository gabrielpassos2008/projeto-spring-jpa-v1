package com.gabriel.projeto_spring_jpa_v1.e2e;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

class E2eTest {

	@Test
	void playwrightCarregaPaginaPublica() {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false)
            );

            Page page = browser.newPage();
            page.navigate("http://localhost:8080/login");
			assertThat(page).hasTitle(Pattern.compile("Document"));
		}
	}

}

