package christmas.validation;

import christmas.domain.Menu;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

class MyOrderValidationTest {

    @ParameterizedTest
    @ValueSource(strings = {"여기 해산물파스타 하나요~", "해산물파스타-하나"})
    @DisplayName("음식 주문 입력값은 특정한 형태를 유지 하면 안되면 에러를 리턴한다.")
    void validateFoodOrderFormat(String input) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            MyOrderValidation.validateOrderInfo(input);
        });
    }

    @ParameterizedTest
    @MethodSource("generateMenuAndMap")
    @DisplayName("다음 저장해야할 음식 검증, 중복 입력시 에러")
    void validateDuplicateFoodOrder(Menu menu, Map<Menu, Integer> map) {
        map.put(Menu.SEAFOOD_PASTA, 1);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            MyOrderValidation.validateFood(menu, map);
        });
    }

    static Stream<Arguments> generateMenuAndMap() {
        return Stream.of(
                Arguments.of(Menu.SEAFOOD_PASTA, new HashMap<Menu, Integer>())
        );
    }
}