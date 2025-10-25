package racingcar.domain;

import java.util.Arrays;
import java.util.List;

public class Cars {
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
            throw new IllegalArgumentException("경주할 자동차 이름 목록은 ',' 으로 끝날 수 없습니다.");
        }
        String[] names = carNames.split(",");
        if (names.length == 0) {
            throw new IllegalArgumentException("경주할 자동차 이름은 하나 이상 입력해야합니다.");
        }
        return names;
    }
}
