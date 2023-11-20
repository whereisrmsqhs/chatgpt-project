package christmas.service;

import christmas.domain.Price;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.*;

class PlannerServiceTest {

    @ParameterizedTest
    @CsvSource(value = {"1000:0", "120000:25,000"}, delimiter = ':')
    @DisplayName("할인전 총 주문 가격이 12만원 이상이면 샴페인 가격을 리턴")
    void calculatePresentationAvailable(Integer inputPrice, String expected) {
        PlannerService service = new PlannerService();
        Price resultPrice = service.includeChampaigneGift(new Price(inputPrice));

        assertThat(resultPrice.convertToOutputType()).isEqualTo(expected);
    }

}