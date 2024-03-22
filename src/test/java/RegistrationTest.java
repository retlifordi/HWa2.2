import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationTest {
    public String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }
    


    String planningDate = generateDate(5);
    @Test
    void shouldTestV1() throws InterruptedException {
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Казань");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(planningDate);
        $("[data-test-id=name] input").setValue("Костеркина Анастасия");
        $("[data-test-id=phone] input").setValue("+79009653384");
        $("[data-test-id=agreement]").click();
        $$("button").get(1).click();
        $(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
        $(".notification__content")
                .shouldHave(Condition.text("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }
    @Test
    void shouldTestV2() throws InterruptedException {
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Минск");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(planningDate);
        $("[data-test-id=name] input").setValue("Костеркина Анастасия");
        $("[data-test-id=phone] input").setValue("+79009653384");
        $("[data-test-id=agreement]").click();
        $$("button").get(1).click();
        $(byText("Доставка в выбранный город недоступна")).shouldBe(visible);
    }
    @Test
    void shouldTestV3() throws InterruptedException {
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Казань");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(planningDate);
        $("[data-test-id=name] input").setValue("Anastasiia kost");
        $("[data-test-id=phone] input").setValue("+79009653384");
        $("[data-test-id=agreement]").click();
        $$("button").get(1).click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(Condition.text("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }
    @Test
    void shouldTestV4() throws InterruptedException {
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Казань");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(planningDate);
        $("[data-test-id=name] input").setValue("");
        $("[data-test-id=phone] input").setValue("+79009653384");
        $("[data-test-id=agreement]").click();
        $$("button").get(1).click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(Condition.text("Поле обязательно для заполнения"));
    }

    @Test
    void shouldTestV5() throws InterruptedException {
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(planningDate);
        $("[data-test-id=name] input").setValue("Костеркина Анастасия");
        $("[data-test-id=phone] input").setValue("+79009653384");
        $("[data-test-id=agreement]").click();
        $$("button").get(1).click();
        $(withText("Поле обязательно для заполнения")).shouldBe(visible);
    }
    @Test
    void shouldTestV6() throws InterruptedException {
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Казань");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(planningDate);
        $("[data-test-id=name] input").setValue("Костеркина Анастасия");
        $("[data-test-id=phone] input").setValue("+7000");
        $("[data-test-id=agreement]").click();
        $$("button").get(1).click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(Condition.text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldTestV7() throws InterruptedException {
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Казань");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(planningDate);
        $("[data-test-id=name] input").setValue("Костеркина Анастасия");
        $("[data-test-id=phone] input").setValue("");
        $("[data-test-id=agreement]").click();
        $$("button").get(1).click();
        $(withText("Поле обязательно для заполнения")).shouldBe(visible);
    }

    @Test
    void shouldTestV8() throws InterruptedException {
        open("http://localhost:9999");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE);
        $("[data-test-id=city] input").setValue("Казань");
        $("[data-test-id=name] input").setValue("Костеркина Анастасия");
        $("[data-test-id=phone] input").setValue("+79009653384");
        $("[data-test-id=agreement]").click();
        $$("button").get(1).click();
        $(withText("Неверно введена дата")).shouldBe(visible);
    }

    @Test
    void shouldTestV9() throws InterruptedException {
        open("http://localhost:9999");
        $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        String planningDateNegative = generateDate(1);
        $("[data-test-id=date] input").setValue(planningDateNegative);
        $("[data-test-id=city] input").setValue("Казань");
        $("[data-test-id=name] input").setValue("Костеркина Анастасия");
        $("[data-test-id=phone] input").setValue("+79009653384");
        $("[data-test-id=agreement]").click();
        $$("button").get(1).click();
        $(withText("Заказ на выбранную дату невозможен")).shouldBe(visible);
    }
    @Test
    void shouldTestV10() throws InterruptedException {
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Казань");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(planningDate);
        $("[data-test-id=name] input").setValue("Костеркина Анастасия");
        $("[data-test-id=phone] input").setValue("+79009653384");
        $$("button").get(1).click();
        $("[data-test-id=agreement].input_invalid .checkbox__text").shouldBe(visible);
    }
}



