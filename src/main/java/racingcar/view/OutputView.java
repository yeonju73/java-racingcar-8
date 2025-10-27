package racingcar.view;

import racingcar.domain.Cars;

public class OutputView {
    private static final String OUTPUT_RESULT_PROMPT_MESSAGE = "\n실행 결과";

    public static void printResultMessage() {
        System.out.println(OUTPUT_RESULT_PROMPT_MESSAGE);
    }

    public static void printRoundResult(Cars cars) {
    }

    public static void printWinner(Cars winners) {
        System.out.println("최종 우승자 : pobi, jun");
    }
}
