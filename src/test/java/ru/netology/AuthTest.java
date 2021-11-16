package ru.netology;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.utilits.LoginPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class AuthTest {
    LoginPage logo = new LoginPage();

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    @Test
    void shouldBePositiveTest() {
        logo.loginActiveUser();
        $(byText("Личный кабинет")).shouldBe(visible);
    }

    @Test
    void shouldBeNegativeTest() {
        logo.loginBlockedUser();
        $("[data-test-id='error-notification']").shouldHave(text("Ошибка! Пользователь заблокирован"));
    }

    @Test
    void shouldBeWrongLoginTest() {
        logo.wrongLogin();
        $("[data-test-id='error-notification']").shouldHave(text("Неверно указан логин или пароль"));
    }

    @Test
    void shouldBeWrongPasswordTest() {
        logo.wrongPassword();
        $("[data-test-id='error-notification']").shouldHave(text("Неверно указан логин или пароль"));
    }
}
