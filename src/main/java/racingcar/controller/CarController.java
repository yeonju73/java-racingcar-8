package racingcar.controller;

import racingcar.domain.AttemptCount;
import racingcar.domain.Cars;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class CarController {
    public void run() {
        String carNames = InputView.readCarNames();
        String attemptInput = InputView.readAttemptCount();

        Cars cars = Cars.of(carNames);
        AttemptCount attemptCount = AttemptCount.of(attemptInput);

        OutputView.printResultMessage();
        moveCarsWithPrintWhileAttemptCount(attemptCount, cars);
        OutputView.printWinner(cars.findWinners());
    }

    private static void moveCarsWithPrintWhileAttemptCount(AttemptCount attemptCount, Cars cars) {
        for (int i = 0; i < attemptCount.getValue(); i++) {
            cars.moveAll();
            OutputView.printRoundResult(cars);
        }
    }
}
