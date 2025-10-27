package racingcar.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class CarTest {
    private Car car;

    @BeforeEach
    void setUp() {
        car = Car.of("pobi");
    }

    @DisplayName("자동차는 4이상일 경우 전진한다")
    @ParameterizedTest
    @ValueSource(ints = {4, 5, 6, 7, 8, 9})
    void move_shouldMoveForward_whenNumberIs4OrMore(int input) {
        // given - @BeforeEach
        // when
        car.move(input);

        // then
        assertThat(car.getPosition()).isEqualTo(1);
    }

    @DisplayName("자동차는 3이하일 경우 멈춘다")
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3})
    void move_shouldStop_whenNumberIsLessThan4(int input) {
        // given - @BeforeEach
        // when
        car.move(input);

        // then
        assertThat(car.getPosition()).isEqualTo(0);
    }
}