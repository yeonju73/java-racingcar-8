package racingcar.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import racingcar.exception.ErrorMessage;

class AttemptCountTest {

    @DisplayName("시도 횟수가 숫자가 아니면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"a", " ", "1a2", "1.5", ""})
    void createAttemptCount_shouldThrowException_whenInputIsNotNumber(String input) {
        // given & when & then
        assertThatThrownBy(() -> AttemptCount.of(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.ATTEMPT_COUNT_NOT_A_NUMBER.getMessage());
    }

    @DisplayName("시도 횟수가 1 이상의 자연수가 아니면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"0", "-1", "-100"})
    void createAttemptCount_shouldThrowException_whenInputIsNotPositive(String input) {
        // given & when & then
        assertThatThrownBy(() -> AttemptCount.of(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.ATTEMPT_COUNT_NOT_POSITIVE.getMessage());
    }

    @DisplayName("시도 횟수가 1 이상의 자연수이면 객체를 성공적으로 생성한다.")
    @ParameterizedTest
    @ValueSource(strings = {"1", "5", "100"})
    void createAttemptCount_shouldSucceed_whenInputIsPositiveNumber(String input) {
        // given & when & then
        assertThatCode(() -> AttemptCount.of(input))
                .doesNotThrowAnyException();

        AttemptCount attemptCount = AttemptCount.of(input);
        assertThat(attemptCount.getValue()).isEqualTo(Integer.parseInt(input));
    }
}