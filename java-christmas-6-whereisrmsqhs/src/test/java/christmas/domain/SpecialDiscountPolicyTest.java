package christmas.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class SpecialDiscountPolicyTest {

    @ParameterizedTest
    @MethodSource("generateMyOrderVisitDateAndExpectResult")
    @DisplayName("예약한 날짜가 특별한 날이면 특별할인이 들어간다.")
    void calculateSpecialDiscount(MyOrder myOrder, Integer visitDate, Price expectedResult) {
        SpecialDiscountPolicy policy = new SpecialDiscountPolicy();
        Price discountPrice = policy.discount(myOrder, visitDate, December.getInstance());

        Assertions.assertThat(discountPrice.convertToOutputType()).isEqualTo(expectedResult.convertToOutputType());
    }

    static Stream<Arguments> generateMyOrderVisitDateAndExpectResult() {
        return Stream.of(
                Arguments.of(new MyOrder("초코케이크-2"), 3, new Price(1000)),
                Arguments.of(new MyOrder("초코케이크-2"), 1, new Price(0))
        );
    }

}