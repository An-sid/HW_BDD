package ru.netology.web.data;

import lombok.Value;

public class DataHelper {
    private DataHelper() {
    }

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    public static VerificationCode getVerificationCodeFor(AuthInfo authInfo) {

        return new VerificationCode("12345");
    }

    public static class CardName {
        private static String firstCard = "5559 0000 0000 0001";
        private static String secondCard = "5559 0000 0000 0002";

        public static String getFirstCard() {
            return firstCard;
        }

        public static String getSecondCard() {
            return secondCard;
        }
    }

}