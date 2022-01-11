package ru.netology.web.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPage;
import ru.netology.web.page.TransferPage;

import static com.codeborne.selenide.Selenide.open;

public class TransferTest {

    DashboardPage dashboardPage = new DashboardPage();


    @Test
    void transferFromFirstToSecondCard() throws Exception {
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        TransferPage transferPage = new TransferPage();
        transferPage.transferMoney(DataHelper.CardName.getSecondCard(), DataHelper.CardName.getFirstCard(),1);
    }

    @Test
    void transferFromSecondToFirstCard() throws Exception {
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        TransferPage transferPage = new TransferPage();
        transferPage.transferMoney(DataHelper.CardName.getFirstCard(), DataHelper.CardName.getSecondCard(),1);
    }

    @Test
    void transferZero() throws Exception {
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        TransferPage transferPage = new TransferPage();
        var beforeTransfer = dashboardPage.getCardBalance(DataHelper.CardName.getSecondCard());
        transferPage.transferMoney(DataHelper.CardName.getFirstCard(), DataHelper.CardName.getSecondCard(),0);
        var afterTransfer = dashboardPage.getCardBalance(DataHelper.CardName.getSecondCard());
        Assertions.assertEquals(beforeTransfer, afterTransfer);
    }

    @Test
    void transferMaxValue() throws Exception {
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        TransferPage transferPage = new TransferPage();
        var total = dashboardPage.getCardBalance(DataHelper.CardName.getSecondCard());
        transferPage.transferMoney(DataHelper.CardName.getFirstCard(), DataHelper.CardName.getSecondCard(),total);
    }
        //Negative test
    @Test
    void tranferOverMaxValue() throws Exception {
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        TransferPage transferPage = new TransferPage();
        var total = dashboardPage.getCardBalance(DataHelper.CardName.getSecondCard());
        transferPage.transferMoney(DataHelper.CardName.getFirstCard(), DataHelper.CardName.getSecondCard(),total + 1);
        System.out.println("ok");
    }

}
