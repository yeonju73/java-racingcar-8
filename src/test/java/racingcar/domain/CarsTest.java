package racingcar.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import racingcar.exception.ErrorMessage;

class CarsTest {
    private static final int MOVING_FORWARD = 4;

    @DisplayName("자동차 이름 목록에 5자를 초과하는 이름이 있으면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"pobi,javaji", "longname", "pobi,woni,sixsix"})
    void createCars_shouldThrowException_whenNameExceedsMaxLength(String input) {
        // given & when & then
        // Cars.of()가 내부적으로 CarName.of()를 호출하며 5자 초과 검증을 수행합니다.
        assertThatThrownBy(() -> Cars.of(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.CAR_NAME_TOO_LONG.getMessage());
    }

    @DisplayName("자동차 이름 목록에 빈 값이 포함되면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"pobi,,woni", ",pobi", "pobi,", "pobi,woni,"})
    void createCars_shouldThrowException_whenNameIsEmptyOrBlank(String input) {
        // given & when & then
        // 빈 문자열이 CarName.of()로 전달되어 예외가 발생합니다.
        assertThatThrownBy(() -> Cars.of(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.CAR_NAME_IS_BLANK.getMessage());
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

    @DisplayName("다양한 시나리오에 대한 우승자를 정확히 판별한다")
    @ParameterizedTest
    @CsvSource({
            "2, 1, 0, 'pobi'",
            "1, 0, 1, 'pobi,jun'",
            "0, 0, 0, 'pobi,woni,jun'",
            "3, 5, 2, 'woni'"
    })
    void findWinners_shouldReturnCorrectWinners(int pobiMoves, int woniMoves, int junMoves,
                                                String expectedWinnerString) {

        // given: CsvSource의 값들을 기반으로 자동차 상태를 설정합니다.
        Cars cars = Cars.of("pobi,woni,jun");
        List<Car> carList = cars.getCars();

        // pobi 이동
        for (int i = 0; i < pobiMoves; i++) {
            carList.getFirst().move(MOVING_FORWARD); // 전진을 보장하는 값
        }
        // woni 이동
        for (int i = 0; i < woniMoves; i++) {
            carList.get(1).move(MOVING_FORWARD);
        }
        // jun 이동
        for (int i = 0; i < junMoves; i++) {
            carList.get(2).move(MOVING_FORWARD);
        }

        // when
        List<String> winners = cars.findWinners();

        // CSV의 기대값과 비교하기 위해 실제 우승자 리스트를 문자열로 변환합니다.
        String actualWinnerString = String.join(",", winners);

        // then
        assertThat(actualWinnerString).isEqualTo(expectedWinnerString);
    }
}