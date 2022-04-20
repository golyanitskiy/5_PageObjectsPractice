package guru.qa.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static java.lang.String.format;

public class RegistrationFormPage {
    Faker faker = new Faker(new Locale("ru"));
    String name = faker.name().firstName();
    String surname = faker.name().lastName();
    String expectedFullName = format("%s %s", name, surname);
    String sex = "Male";
    String email = faker.bothify("?????????????###@?????.??");
    String phoneNumber = faker.number().digits(10);
    Date date = faker.date().birthday(16, 99);
    LocalDate desiredDate = date.toInstant()
            .atZone(ZoneId.systemDefault())
            .toLocalDate();
    String day = String.valueOf(desiredDate.getDayOfMonth());
    String month = String.valueOf(desiredDate.getMonth()).toLowerCase();
    String capitalizedMonth = month.substring(0, 1).toUpperCase() + month.substring(1);
    String year = String.valueOf(desiredDate.getYear());
    String subject = "Computer Science";
    String hobby = "Reading";
    String expectedDateOfBirth = format("%s %s,%s", day, capitalizedMonth, year);
    String address = faker.address().fullAddress();
    String filePath = "image.jpg";
    String state = "Rajasthan";
    String city = "Jaipur";
    SelenideElement formSubmitButton = $("#submit");
    SelenideElement confirmationCloseButton = $("#closeLargeModal");

    //    actions
    public RegistrationFormPage openPage() {
        Selenide.open("/automation-practice-form");
        zoom(0.75); // иначе не дает кликнуть по штату и городу

        return this;
    }

    public RegistrationFormPage setFirstName() {
        $("#firstName").setValue(name);

        return this;
    }

    public RegistrationFormPage setSurname() {
        $("#lastName").setValue(surname);

        return this;
    }

    public RegistrationFormPage setEmail() {
        $("#userEmail").setValue(email);

        return this;
    }

    public RegistrationFormPage selectGender() {
        $("#genterWrapper").$(byText(sex)).click();

        return this;
    }

    public RegistrationFormPage setPhone() {
        $("#userNumber").setValue(phoneNumber);

        return this;
    }

    public RegistrationFormPage setBirthDate() {
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption(capitalizedMonth);
        $(".react-datepicker__year-select").selectOption(year);
        $$(".react-datepicker__day:not(.react-datepicker__day--outside-month)").findBy(text(day)).click();

        return this;
    }

    public RegistrationFormPage setSubject() {
        $("#subjectsInput").setValue("Co");
        $$("[id^='react-select-2-option']").findBy(text(subject)).click();

        return this;
    }

    public RegistrationFormPage selectHobby() {
        $("#hobbiesWrapper").$(byText(hobby)).click();
        return this;
    }

    public RegistrationFormPage imageUpload() {
        $("#uploadPicture").uploadFromClasspath(filePath);

        return this;
    }

    public RegistrationFormPage setAddress() {
        $("#currentAddress").setValue(address);

        return this;
    }

    public RegistrationFormPage setState() {
        $("#state").click();
        $$("[id^='react-select-3-option']").findBy(text(state)).click();

        return this;
    }

    public RegistrationFormPage setCity() {
        $("#city").click();
        $$("[id^='react-select-4-option']").findBy(text(city)).click();

        return this;
    }

    public RegistrationFormPage submitForm() {
        formSubmitButton.click();

        return this;
    }

    public RegistrationFormPage checkFormResult() {
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));

        $(".table-responsive").shouldHave(
                text(expectedFullName),
                text(email),
                text(sex),
                text(phoneNumber),
                text(expectedDateOfBirth),
                text(subject),
                text(hobby),
                text(filePath),
                text(address),
                text(state + ' ' + city));

        return this;
    }

    public void closeConfirmation() {
        confirmationCloseButton.click();
    }
}
