package ru.netology;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.utilits.LoginPage;
import ru.netology.utilits.RegistrationInfo;
import ru.netology.utilits.UserGenerator;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class AuthTest {
    LoginPage logo = new LoginPage();
    RegistrationInfo activeUser = UserGenerator.getUser("active");
    RegistrationInfo blockedUser = UserGenerator.getUser("blocked");
    String randomLogin = UserGenerator.getRandomLogin();
    String randomPassword = UserGenerator.getRandomPassword();

    @BeforeEach
    void setUp() {
        open("http://localhost:9999/");
    }

    @Test
    void shouldBePositiveTest() {
        logo.loginUser(activeUser.getLogin(), activeUser.getPassword());
        $(byText("Личный кабинет")).shouldBe(visible);
    }

    @Test
    void shouldBeNegativeTest() {
        logo.loginUser(blockedUser.getLogin(), blockedUser.getPassword());
        $("[data-test-id='error-notification']").shouldHave(text("Ошибка! Пользователь заблокирован"));
    }

    @Test
    void shouldBeWrongLoginTest() {
        logo.loginUser(randomLogin, activeUser.getPassword());
        $("[data-test-id='error-notification']").shouldHave(text("Неверно указан логин или пароль"));
    }

    @Test
    void shouldBeWrongPasswordTest() {
        logo.loginUser(activeUser.getLogin(), randomPassword);
        $("[data-test-id='error-notification']").shouldHave(text("Неверно указан логин или пароль"));
    }
}
