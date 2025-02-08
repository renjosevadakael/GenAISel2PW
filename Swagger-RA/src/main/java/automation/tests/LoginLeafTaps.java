package automation.tests;

import com.microsoft.playwright.*;

public class LoginLeafTaps {
    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(100));
            BrowserContext context = browser.newContext(new BrowserContextOptions().setViewportSize(1920, 1080));
            Page page = context.newPage();

            // Navigate to LeafTaps login page
            page.navigate("http://www.leaftaps.com/opentaps/control/main");

            // Enter username
            page.fill("#username", "demosalesmanager");

            // Enter password
            page.fill("#password", "crmsfa");

            // Click login button
            page.click(".decorativeSubmit");

            // Click CRM/SFA link
            page.click("text=\"CRM/SFA\"");

            // Close browser
            browser.close();
        }
    }
}