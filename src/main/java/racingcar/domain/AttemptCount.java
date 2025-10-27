package racingcar.domain;

import racingcar.exception.ErrorMessage;

public class AttemptCount {
    private final int value;

    private AttemptCount(int value) {
        this.value = value;
    }

    public static AttemptCount of(String input) {
        int parsedValue = validateAndParse(input);
        return new AttemptCount(parsedValue);
    }

    private static int validateAndParse(String input) {
        try {
            int number = Integer.parseInt(input);
            validateIsPositive(number);
            return number;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.ATTEMPT_COUNT_NOT_A_NUMBER.getMessage());
        }
    }

    private static void validateIsPositive(int number) {
        if (number <= 0) {
            throw new IllegalArgumentException(ErrorMessage.ATTEMPT_COUNT_NOT_POSITIVE.getMessage());
        }
    }

    public int getValue() {
        return value;
    }
}
