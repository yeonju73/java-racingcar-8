package racingcar.domain;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class CarsTest {
    @DisplayName("자동차 이름 목록에 5자를 초과하는 이름이 있으면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"pobi,javaji", "longname", "pobi,woni,sixsix"})
    void createCars_shouldThrowException_whenNameExceedsMaxLength(String input) {
        // given & when & then
        // Cars.of()가 내부적으로 CarName.of()를 호출하며 5자 초과 검증을 수행합니다.
        assertThatThrownBy(() -> Cars.of(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("자동차 이름은 5자 이하여야 합니다.");
    }

    @DisplayName("자동차 이름 목록에 빈 값이 포함되면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"pobi,,woni", ",pobi"})
    void createCars_shouldThrowException_whenNameIsEmptyOrBlank(String input) {
        // given & when & then
        // 빈 문자열이 CarName.of()로 전달되어 예외가 발생합니다.
        assertThatThrownBy(() -> Cars.of(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("자동차 이름은 비어있을 수 없습니다.");
    }

    @DisplayName("자동차 이름 목록이 쉼표로 끝나면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"pobi,", "pobi,woni,"})
    void createCars_shouldThrowException_endWithMar(String input) {
        // given & when & then
        // 빈 문자열이 CarName.of()로 전달되어 예외가 발생합니다.
        assertThatThrownBy(() -> Cars.of(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("경주할 자동차 이름 목록은 ',' 으로 끝날 수 없습니다.");
    }

    @DisplayName("올바른 자동차 이름 목록으로 Cars 객체를 생성한다.")
    @ParameterizedTest
    @ValueSource(strings = {"pobi,woni", "jun", "pobi,jun,woni"})
    void createCars_shouldSucceed_withValidNames(String input) {
        // given & when & then
        // 예외가 발생하지 않으면 테스트 성공입니다.
        assertThatCode(() -> Cars.of(input))
                .doesNotThrowAnyException();
    }
}