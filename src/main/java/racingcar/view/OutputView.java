package racingcar.view;

import java.util.List;
import racingcar.domain.Car;
import racingcar.domain.Cars;

public class OutputView {
    private static final String OUTPUT_RESULT_PROMPT_MESSAGE = "\n실행 결과";

    public static void printResultMessage() {
        System.out.println(OUTPUT_RESULT_PROMPT_MESSAGE);
    }

    public static void printRoundResult(Cars cars) {
        for (Car car : cars.getCars()) {
            printCarPosition(car);
        }
        System.out.println();
    }

    private static void printCarPosition(Car car) {
        String positionMarker = "-".repeat(car.getPosition());
        System.out.println(car.getName() + " : " + positionMarker);
    }

    public static void printWinner(List<String> winners) {
        String winnerText = String.join(", ", winners);
        System.out.println("최종 우승자 : " + winnerText);
    }
}
