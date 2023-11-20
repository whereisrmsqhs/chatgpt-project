package christmas.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

class MyRequirementTest {

    @ParameterizedTest
    @MethodSource("generteMyOrder")
    @DisplayName("할인전 내가 주문한 메뉴의 금액 계산")
    void calculateTotalAmountBeforeDiscount(MyOrder myOrder, String expectedPrice) {
        System.out.println(myOrder);
        MyRequirement requirement = new MyRequirement(myOrder, 1);
        Price totalAmountBeforeDiscount = requirement.calculateTotalAmountBeforeDiscount();

        assertThat(totalAmountBeforeDiscount.convertToOutputType()).isEqualTo(expectedPrice);
    }

    private static Stream<Arguments> generteMyOrder() {
        return Stream.of(
                Arguments.of(new MyOrder("티본스테이크-1,초코케이크-2,레드와인-1"), "145,000")
        );
    }

}