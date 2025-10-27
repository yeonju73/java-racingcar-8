package racingcar.domain;

public class Car {
    private static final int MOVE_THRESHOLD = 4;
    private final CarName name;
    private int position = 0;

    private Car(CarName name) {
        this.name = name;
    }

    public static Car of(String carName) {
        return new Car(CarName.of(carName));
    }

    public String getName() {
        return name.getName();
    }

    public int getPosition() {
        return position;
    }

    public void move(int randomNumber) {
        if (randomNumber >= MOVE_THRESHOLD) {
            this.position++;
        }
    }
}
