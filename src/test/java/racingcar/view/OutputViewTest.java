package racingcar.view;


import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.domain.Cars;

class OutputViewTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        // 테스트 시작 전 System.out의 출력을 outContent로 보냄
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        // 테스트 끝난 후 System.out을 원래대로 복구
        System.setOut(originalOut);
    }

    @Test
    @DisplayName("라운드의 모든 차의 진행 결과를 출력한다.")
    void printRoundResult_shouldPrintAllCarPositions() {
        // given: pobi는 2칸, woni는 1칸 전진한 상태를 만듭니다.
        Cars cars = Cars.of("pobi,woni");
        cars.getCars().get(0).move(4);
        cars.getCars().get(0).move(4);
        cars.getCars().get(1).move(4);

        // when: printRoundResult를 호출합니다.
        OutputView.printRoundResult(cars);

        // then: 예상되는 전체 출력 문자열과 일치하는지 확인합니다.
        String expectedOutput = "pobi : --" + System.lineSeparator() +
                "woni : -" + System.lineSeparator() +
                System.lineSeparator(); // 라운드 끝의 빈 줄까지 포함

        assertThat(outContent.toString()).isEqualTo(expectedOutput);
    }

}