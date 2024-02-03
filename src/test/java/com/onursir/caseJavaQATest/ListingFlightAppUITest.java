package com.onursir.caseJavaQATest;
import com.microsoft.playwright.*;

public class ListingFlightAppUITest {
    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            Page page = browser.newPage();

            page.navigate("https://flights-app.pages.dev/");
            page.waitForLoadState();

            page.click("xpath=//button[@id='headlessui-combobox-button-:R1a9lla:']");
            page.click("text=Istanbul");


            page.click("//*[@id='headlessui-combobox-button-:R1ahlla:']");
            page.click("text=Los Angeles");

            page.waitForSelector(".mb-10");

            String foundItemsText = page.innerText(".mb-10");
            int listedItems = Integer.parseInt(foundItemsText.split(" ")[1]);
            int displayedFlights = page.querySelectorAll("li.overflow-hidden.rounded-xl.border.border-gray-200").size();

            if (listedItems == displayedFlights) {
                System.out.println("Test successful: The number of flights listed and displayed matches.");
            } else {
                System.out.println("Test failed: The number of flights listed and displayed do not match.");
            }

            browser.close();
        }
    }
}