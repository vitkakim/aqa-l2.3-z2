package ru.netology.utilits;

import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.util.Locale;

import static io.restassured.RestAssured.given;

public class UserGenerator {
    private static final Faker faker = new Faker(new Locale("en"));
    private static final RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(9999)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    private UserGenerator() {
    }

    public static void setUpUser(RegistrationInfo user) {
        given()
                .spec(requestSpec)
                .body(user)
                .when()
                .post("/api/system/users")
                .then()
                .statusCode(200);
    }

    public static String getRandomLogin() {
        String login = faker.name().firstName();
        return login;
    }

    public static String getRandomPassword() {
        String password = faker.name().firstName();
        return password;
    }

    public static RegistrationInfo getUser(String status) {
        RegistrationInfo user = new RegistrationInfo(getRandomLogin(), getRandomPassword(), status);
        setUpUser(user);
        return user;
    }
}
