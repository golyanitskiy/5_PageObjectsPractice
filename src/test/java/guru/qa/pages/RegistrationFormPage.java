package guru.qa.pages;

import com.codeborne.selenide.Selenide;
import guru.qa.data.RegistrationFormData;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationFormPage {
    RegistrationFormData data = new RegistrationFormData();
    //    actions
    public RegistrationFormPage openPage() {
        Selenide.open("/automation-practice-form");
        zoom(0.75); // иначе не дает кликнуть по штату и городу

        return this;
    }

    public RegistrationFormPage setFirstName() {
        $("#firstName").setValue(data.name);

        return this;
    }

    public RegistrationFormPage setSurname() {
        $("#lastName").setValue(data.surname);

        return this;
    }

    public RegistrationFormPage setEmail() {
        $("#userEmail").setValue(data.email);

        return this;
    }

    public RegistrationFormPage selectGender() {
        $("#genterWrapper").$(byText(data.sex)).click();

        return this;
    }

    public RegistrationFormPage setPhone() {
        $("#userNumber").setValue(data.phoneNumber);

        return this;
    }

    public RegistrationFormPage setBirthDate() {
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption(data.capitalizedMonth);
        $(".react-datepicker__year-select").selectOption(data.year);
        $$(".react-datepicker__day:not(.react-datepicker__day--outside-month)")
                .findBy(text(data.day)).click();

        return this;
    }

    public RegistrationFormPage setSubject() {
        $("#subjectsInput").setValue("Co");
        $$("[id^='react-select-2-option']").findBy(text(data.subject)).click();

        return this;
    }

    public RegistrationFormPage selectHobby() {
        $("#hobbiesWrapper").$(byText(data.hobby)).click();
        return this;
    }

    public RegistrationFormPage imageUpload() {
        $("#uploadPicture").uploadFromClasspath(data.filePath);

        return this;
    }

    public RegistrationFormPage setAddress() {
        $("#currentAddress").setValue(data.address);

        return this;
    }

    public RegistrationFormPage setState() {
        $("#state").click();
        $$("[id^='react-select-3-option']").findBy(text(data.state)).click();

        return this;
    }

    public RegistrationFormPage setCity() {
        $("#city").click();
        $$("[id^='react-select-4-option']").findBy(text(data.city)).click();

        return this;
    }

    public RegistrationFormPage submitForm() {
        data.formSubmitButton.click();

        return this;
    }

    public RegistrationFormPage checkFormResult() {
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));

        $(".table-responsive").shouldHave(
                text(data.expectedFullName),
                text(data.email),
                text(data.sex),
                text(data.phoneNumber),
                text(data.expectedDateOfBirth),
                text(data.subject),
                text(data.hobby),
                text(data.filePath),
                text(data.address),
                text(data.state + ' ' + data.city));

        return this;
    }

    public void closeConfirmation() {
        data.confirmationCloseButton.click();
    }
}
