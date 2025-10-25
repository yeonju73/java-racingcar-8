package racingcar.domain;

public class Car {
    private final CarName name;
    private int position = 0;

    private Car(CarName name) {
        this.name = name;
    }

    public static Car of(String carName) {
        return new Car(CarName.of(carName));
    }

    public String getCarInfo() {
        return this.name.getName() + " : " + this.position;
    }
}
