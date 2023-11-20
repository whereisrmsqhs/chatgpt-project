package christmas.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.*;

class MyOrderTest {

    @ParameterizedTest
    @CsvSource(value = {"티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1:142,000"}, delimiter = ':')
    @DisplayName("주문한 메뉴 정보가 주어졌을 때 할인전 총 금액을 계산하는 기능")
    void calculateTotalAmountBeforeDiscount(String textOrder, String expectedResult) {
        MyOrder myOrder = new MyOrder(textOrder);
        Price totalAmountBeforeDiscount = myOrder.calculateTotalAmountBeforeDiscount();

        Assertions.assertThat(totalAmountBeforeDiscount.convertToOutputType()).isEqualTo(expectedResult);
    }

    @ParameterizedTest
    @CsvSource(value = {"티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1:4,046"}, delimiter = ':')
    @DisplayName("평일 할인은 내 주문 내역중 디저트 하나당 2023원 할인, 그래서 총 얼마 할인하는지 찾는 기능")
    void weekDayTotalDiscountAmount(String textOrder, String expectedResult) {
        MyOrder myOrder = new MyOrder(textOrder);
        Price price = myOrder.calculateDiscountInWeekDay();

        Assertions.assertThat(price.convertToOutputType()).isEqualTo(expectedResult);
    }

    @ParameterizedTest
    @CsvSource(value = {"티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1:4,046", "티본스테이크-2,바비큐립-1:6,069"}, delimiter = ':')
    @DisplayName("주말 할인은 주문 내역중 메인 메뉴 하나당 2023원 할인, 주말에는 총 엄마나 할인하는지 찾는 기능 테스트")
    void weekEndTotalDiscountAmount(String textOrder, String expectedResult) {
        MyOrder myOrder = new MyOrder(textOrder);
        Price price = myOrder.calculateDiscountInWeekEnd();

        Assertions.assertThat(price.convertToOutputType()).isEqualTo(expectedResult);
    }

}