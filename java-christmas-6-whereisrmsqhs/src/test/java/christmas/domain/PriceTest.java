package christmas.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class PriceTest {

    @ParameterizedTest
    @CsvSource(value = {"8500:8,500", "142000:142,000", "1200:1,200", "25000:25,000", "1100000:1,100,000"}, delimiter = ':')
    @DisplayName("일반 정수값을 출력하기 좋은 컴마 형식으로 변환한다.")
    void convertPriceOutputFormat(int inputFormatNumber, String outputFormatNumber) {
        Price price = new Price(inputFormatNumber);
        Assertions.assertThat(price.convertToOutputType()).isEqualTo(outputFormatNumber);
    }
}