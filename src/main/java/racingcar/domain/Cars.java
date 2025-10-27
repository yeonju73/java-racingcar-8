package racingcar.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.Arrays;
import java.util.List;
import racingcar.exception.ErrorMessage;

public class Cars {
    private static final int RANDOM_MIN = 0;
    private static final int RANDOM_MAX = 9;
    private final List<Car> cars;

    private Cars(List<Car> cars) {
        this.cars = cars;
    }

    public static Cars of(String carNames) {
        return new Cars(Arrays.stream(getSplit(carNames))
                .map(Car::of)
                .toList());
    }

    private static String[] getSplit(String carNames) {
        String[] names = carNames.split(",", -1);
        validationCarNamesNotEmpty(names);
        return names;
    }

    private static void validationCarNamesNotEmpty(String[] names) {
        if (names.length == 0) {
            throw new IllegalArgumentException(ErrorMessage.AT_LEAST_ONE_CAR.getMessage());
        }
    }

    public void moveAll() {
        for (Car car : cars) {
            int randomNumber = Randoms.pickNumberInRange(RANDOM_MIN, RANDOM_MAX);
            car.move(randomNumber);
        }
    }

    public List<Car> getCars() {
        return cars;
    }

    public List<String> findWinners() {
        int maxPosition = findMaxPosition();
        return cars.stream()
                .filter(car -> car.isAt(maxPosition))
                .map(Car::getName)
                .toList();
    }

    private int findMaxPosition() {
        return cars.stream()
                .mapToInt(Car::getPosition)
                .max()
                .orElse(0);
    }

}
