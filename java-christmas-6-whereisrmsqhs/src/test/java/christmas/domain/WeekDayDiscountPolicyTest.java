package christmas.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class WeekDayDiscountPolicyTest {

    @ParameterizedTest
    @MethodSource("generateMyOrderVisitDateAndExpectResult")
    @DisplayName("평일에 얻을 수 있는 할인 가격, 총 계산")
    void calculateWeekDayDiscountAmount(MyOrder myOrder, Integer visitDate, Price expectedResult) {
        WeekDayDiscountPolicy policy = new WeekDayDiscountPolicy();
        Price discountPrice = policy.discount(myOrder, visitDate, December.getInstance());

        Assertions.assertThat(discountPrice.convertToOutputType()).isEqualTo(expectedResult.convertToOutputType());
    }

    static Stream<Arguments> generateMyOrderVisitDateAndExpectResult() {
        return Stream.of(
                Arguments.of(new MyOrder("초코케이크-2"), 3, new Price(4046)),
                Arguments.of(new MyOrder("초코케이크-2"), 1, new Price(0))
        );
    }

}