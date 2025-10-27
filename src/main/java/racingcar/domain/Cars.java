package racingcar.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.Arrays;
import java.util.List;
import racingcar.exception.ErrorMessage;

public class Cars {
    private static final int RANDOM_MIN = 0;
    private static final int RANDOM_MAX = 9;
    private List<Car> cars;

    private Cars(List<Car> cars) {
        this.cars = cars;
    }

    public static Cars of(String carNames) {
        return new Cars(Arrays.stream(getSplit(carNames))
                .map(Car::of)
                .toList());
    }

    private static String[] getSplit(String carNames) {
        if (carNames.endsWith(",")) {
            throw new IllegalArgumentException(ErrorMessage.INPUT_ENDS_WITH_COMMA.getMessage());
        }
        String[] names = carNames.split(",");
        if (names.length == 0) {
            throw new IllegalArgumentException(ErrorMessage.AT_LEAST_ONE_CAR.getMessage());
        }
        return names;
    }

    public void moveAll() {
        for (Car car : cars) {
            int randomNumber = Randoms.pickNumberInRange(RANDOM_MIN, RANDOM_MAX);
            car.move(randomNumber);
        }
    }
}
