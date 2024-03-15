import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.*;

public class RegistrationTest {
    public String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }



    public String generateDate2(int days) {
        return LocalDate.now().minusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    String planningDateNegative = generateDate2(5);

    @Test
    void shouldTestV1() throws InterruptedException {
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Москва");
        String planningDate = generateDate(4);
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(planningDate);
        $("[data-test-id=name] input").setValue("Костеркина Анастасия");
        $("[data-test-id=phone] input").setValue("+79009653384");
        $("[data-test-id=agreement]").click();
        $$("button").get(1).click();
        $("button.button").click();
        $(".notification__content")
                .shouldHave(Condition.text("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(15))
                .shouldBe(Condition.visible, Duration.ofSeconds(15));
    }
}



