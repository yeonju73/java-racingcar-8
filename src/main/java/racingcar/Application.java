package racingcar;

import racingcar.domain.Cars;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class Application {
    public static void main(String[] args) {
        String carNames = InputView.readCarNames();
        String attemptCount = InputView.readAttemptCount();

        System.out.println(attemptCount);
        Cars cars = Cars.of(carNames);

        OutputView.printResultMessage();
    }
}
