package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class TransferPage {
    private SelenideElement transferAmount = $("[data-test-id='amount'] .input__control");
    private SelenideElement transferSource = $("[data-test-id='from'] .input__control");
    private SelenideElement tranferBtn = $("[data-test-id='action-transfer']");
    private SelenideElement notification = $(".notification");

    DashboardPage dashboardPage = new DashboardPage();

    public void transferMoney(String cardDest, String cardFrom, int amount) throws Exception {
        int destCardBalance = dashboardPage.getCardBalance(cardDest);
        int fromCardBalance = dashboardPage.getCardBalance(cardFrom);
        dashboardPage.fiilCard(cardDest);
        transferAmount.setValue(String.valueOf(amount));
        transferSource.setValue(cardFrom);
        tranferBtn.click();
        if (amount > fromCardBalance && !notification.isDisplayed()) {
            throw new Exception("something wrong \n" + "The transfer amount is more than the balance of the card \n" +
                    "The transfer was successful.Source card balance is negative now:" + fromCardBalance + "\n"
            + "But card " + cardFrom + " balance is: " + destCardBalance + "\n");
        }
        int destCardBalanceAfterTranfer = dashboardPage.getCardBalance(cardDest);
        int fromCardBalanceAfterTranfer = dashboardPage.getCardBalance(cardFrom);
        if (destCardBalanceAfterTranfer != destCardBalance + amount || fromCardBalanceAfterTranfer != fromCardBalance - amount) {
            throw new Exception("something wrong \n" + "please, check the transfer amount and card balance \n");

        }
    }

}

