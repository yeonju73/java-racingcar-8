package racingcar.exception;

public enum ErrorMessage {
    ATTEMPT_COUNT_NOT_A_NUMBER("시도 횟수는 숫자여야 합니다."),
    ATTEMPT_COUNT_NOT_POSITIVE("시도 횟수는 1 이상의 자연수여야 합니다."),
    CAR_NAME_IS_BLANK("자동차 이름은 비어있을 수 없습니다."),
    CAR_NAME_TOO_LONG("자동차 이름은 5자 이하여야 합니다."),
    AT_LEAST_ONE_CAR("경주할 자동차 이름은 하나 이상 입력해야합니다.");

    private static final String ERROR_PREFIX = "[ERROR] ";
    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return ERROR_PREFIX + message;
    }
}
