package guru.qa.data;

import com.codeborne.selenide.SelenideElement;
import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;

import static com.codeborne.selenide.Selenide.$;
import static java.lang.String.format;

public class RegistrationFormData {

    Faker faker = new Faker(new Locale("ru"));
    public String name = faker.name().firstName();
    public String surname = faker.name().lastName();
    public String expectedFullName = format("%s %s", name, surname);
    public String sex = "Male";
    public String email = faker.bothify("?????????????###@?????.??");
    public String phoneNumber = faker.number().digits(10);
    Date date = faker.date().birthday(16, 99);
    LocalDate desiredDate = date.toInstant()
            .atZone(ZoneId.systemDefault())
            .toLocalDate();
    public String day = String.valueOf(desiredDate.getDayOfMonth());
    String month = String.valueOf(desiredDate.getMonth()).toLowerCase();
    public String capitalizedMonth = month.substring(0, 1).toUpperCase() + month.substring(1);
    public String year = String.valueOf(desiredDate.getYear());
    public String subject = "Computer Science";
    public String hobby = "Reading";
    public String expectedDateOfBirth = format("%s %s,%s", day, capitalizedMonth, year);
    public String address = faker.address().fullAddress();
    public String filePath = "image.jpg";
    public String state = "Rajasthan";
    public String city = "Jaipur";
    public SelenideElement formSubmitButton = $("#submit");
    public SelenideElement confirmationCloseButton = $("#closeLargeModal");
}
