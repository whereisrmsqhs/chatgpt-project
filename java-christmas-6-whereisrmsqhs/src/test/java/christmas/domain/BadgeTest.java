package christmas.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class BadgeTest {

    @ParameterizedTest
    @MethodSource("generatePriceAndExpectResult")
    @DisplayName("Price가 주어졌을 때 얻을 수 있는 배지를 리턴")
    void earnObtainableBadge(Price totalDiscount, Badge expectedBadge) {
        Badge calculatedObatainableBadge = Badge.calculateObatainableBadge(totalDiscount);

        Assertions.assertThat(calculatedObatainableBadge).isEqualTo(expectedBadge);
    }

    static Stream<Arguments> generatePriceAndExpectResult() {
        return Stream.of(
                Arguments.of(new Price(4900), Badge.NONE),
                Arguments.of(new Price(24000), Badge.SANTA)
        );
    }

}