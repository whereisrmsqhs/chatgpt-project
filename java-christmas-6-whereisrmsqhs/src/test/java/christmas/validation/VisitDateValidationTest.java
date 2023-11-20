package christmas.validation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class VisitDateValidationTest {

    @ParameterizedTest
    @CsvSource(value = {"asd:new NumberFormatException()", "100:new IllegalArgumentException()"}, delimiter = ':')
    @DisplayName("만약에 방문한 날짜가 숫자가 아니거나, 아니면 1과 31사이가 아니면 예외")
    void visitInputValidation(String inputDate, Exception e) {
        Assertions.assertThrows(e.getClass(), () -> {
            VisitDateValidation.validateVisitDate(inputDate);
        });
    }
}