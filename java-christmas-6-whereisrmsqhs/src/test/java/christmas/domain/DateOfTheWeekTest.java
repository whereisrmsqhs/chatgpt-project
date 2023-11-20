package christmas.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static christmas.domain.DateOfTheWeek.MONDAY;
import static christmas.domain.DateOfTheWeek.SUNDAY;
import static org.junit.jupiter.api.Assertions.*;

class DateOfTheWeekTest {

    @ParameterizedTest
    @MethodSource("generateDateOfTheWeekAndDays")
    @DisplayName("일요일 혹은 25일 당일은 특별 할인이 적용돠는 날이다.")
    void setSpecialDay(DateOfTheWeek dateOfTheWeek, Integer day, boolean expectedResult) {
        DateOfTheWeek date = dateOfTheWeek;
        boolean isItSpecialDay = date.isSpecialDay(day);

        Assertions.assertThat(isItSpecialDay).isEqualTo(expectedResult);
    }

    private static Stream<Arguments> generateDateOfTheWeekAndDays() {
        return Stream.of(
                Arguments.of(SUNDAY, 3, true),
                Arguments.of(MONDAY, 4, false),
                Arguments.of(MONDAY, 25, true)
        );
    }

    @ParameterizedTest
    @CsvSource(value = {"MONDAY:TUESDAY", "SUNDAY:MONDAY"}, delimiter = ':')
    @DisplayName("지금 요일이 주어졌을 때, 다음 요일을 반환하는 메소드")
    void convertToNextDateOfTheWeek(DateOfTheWeek current, DateOfTheWeek expectedDate) {
        DateOfTheWeek nextDateOfTheWeek = current.calculateNextDateOfTheWeek();
        Assertions.assertThat(nextDateOfTheWeek).isEqualTo(expectedDate);
    }

    @ParameterizedTest
    @CsvSource(value = {"MONDAY:true", "FRIDAY:false"}, delimiter = ':')
    @DisplayName("주어진 Date가 평일인지 확인하는 메소드")
    void checkIsItWeekDay(DateOfTheWeek date, boolean expectedResult) {
        boolean weekDay = date.isWeekDay();

        Assertions.assertThat(weekDay).isEqualTo(expectedResult);
    }

}