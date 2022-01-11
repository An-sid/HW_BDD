package ru.netology.web.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.*;

public class DashboardPage {
    private ElementsCollection fillBtns = $$("[data-test-id='action-deposit']");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";

    public DashboardPage() {
    }

    public void fiilCard(String cardNumber) {
        if (cardNumber.contains("0001")) {
            fillBtns.first().click();
        } else {
            fillBtns.last().click();
        }
    }

    public int getCardBalance(String i) {
        var index = i.substring(15);
        var card = $x("//*[contains(text(), '" + index + "')]");
        var text = card.text();
        return extractBalance(text);
    }

    private int extractBalance(String text) {
        var start = text.indexOf(balanceStart);
        var finish = text.indexOf(balanceFinish);
        var value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

}



