package com.onursir.caseJavaQATest;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.LoadState;

public class SearchFlightAppUITest {
    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            Page page = browser.newPage();

            page.navigate("https://flights-app.pages.dev/");
            page.waitForLoadState();


            selectCity(page, "headlessui-combobox-button-:R1a9lla:", "Istanbul");
            selectCity(page, "headlessui-combobox-button-:R1ahlla:", "Istanbul");

            // Check if the application allows the same "From" and "To" values
            boolean isSameValueAllowed = page.isVisible("text=uçuş bulunmuyor");

            if (isSameValueAllowed) {
                System.out.println("Bug Detected: The same values can be entered for 'From' and 'To'.");

            } else {
                System.out.println("Test Passed: Cannot enter the same values for 'From' and 'To'.");
            }
            browser.close();
        }
    }
    private static void selectCity(Page page, String buttonId, String city) {
        page.click(String.format("xpath=//button[@id='%s']", buttonId));
        page.click(String.format("text=%s", city));
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }
}
