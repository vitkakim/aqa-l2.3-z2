package ru.netology.utilits;

import com.github.javafaker.Faker;

import java.util.Locale;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private Faker faker = new Faker(new Locale("en"));
    private UserGenerator generator = new UserGenerator();

    public void loginActiveUser() {
        $("[data-test-id='login'] input").setValue(generator.active().getLogin());
        $("[data-test-id='password'] input").setValue(generator.active().getPassword());
        $("[data-test-id='action-login']").click();
    }

    public void loginBlockedUser() {
        $("[data-test-id='login'] input").setValue(generator.blocked().getLogin());
        $("[data-test-id='password'] input").setValue(generator.blocked().getPassword());
        $("[data-test-id='action-login']").click();
    }

    public void wrongLogin() {
        $("[data-test-id='login'] input").setValue(faker.name().firstName());
        $("[data-test-id='password'] input").setValue(generator.active().getPassword());
        $("[data-test-id='action-login']").click();
    }

    public void wrongPassword() {
        $("[data-test-id='login'] input").setValue(generator.active().getLogin());
        $("[data-test-id='password'] input").setValue(faker.internet().password());
        $("[data-test-id='action-login']").click();
    }
}
