package christmas.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class MenuTest {

    @ParameterizedTest
    @CsvSource(value = {"티본스테이크:T_BONE_STEAK", "크리스마스파스타:CHRISTMAS_PASTA", "레드와인:RED_WINE"}, delimiter = ':')
    @DisplayName("문자열의 형태로 음식 이름 주어졌을 때, 실제 Menu안에 있는 음식값을 가져오기")
    void bringRealMenuField(String inputFood, Menu convertedFood) {
        Menu myMenu = Menu.findMyMenu(inputFood);

        Assertions.assertThat(myMenu).isEqualTo(convertedFood);
    }

    @Test
    @DisplayName("음료가 아니면 true, 음료면 false 리턴")
    void isItDrink() {
        boolean notDrink = Menu.BBQ_RIBS.isNotDrink();
        boolean isDrink = Menu.CHAMPAGNE.isNotDrink();

        Assertions.assertThat(notDrink).isEqualTo(true);
        Assertions.assertThat(isDrink).isEqualTo(false);
    }

}