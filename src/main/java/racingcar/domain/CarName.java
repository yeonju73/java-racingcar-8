package racingcar.domain;

import racingcar.exception.ErrorMessage;

public class CarName {
    private static final int MAX_NAME_LENGTH = 5;
    private final String name;

    private CarName(String name) {
        this.name = name;
    }

    public static CarName of(String name) {
        validateName(name);
        return new CarName(name);
    }

    private static void validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(ErrorMessage.CAR_NAME_IS_BLANK.getMessage());
        }
        if (name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException(ErrorMessage.CAR_NAME_TOO_LONG.getMessage());
        }
    }

    public String getName() {
        return name;
    }
}
