package guru.qa.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import guru.qa.pages.RegistrationFormPage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class RegistrationFormPageTest {

    @BeforeAll
    static void setUp() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
    }

    @AfterAll
    static void closeDriver() {
        Selenide.closeWebDriver();
    }

    @Test
    void formFilling() {
        RegistrationFormPage page = new RegistrationFormPage();

        page.openPage()
                .setFirstName()
                .setSurname()
                .setEmail()
                .selectGender()
                .setPhone()
                .setBirthDate()
                .setSubject()
                .selectHobby()
                .imageUpload()
                .setAddress()
                .setState()
                .setCity()

                .submitForm()

                .checkFormResult()
                .closeConfirmation();
    }
}