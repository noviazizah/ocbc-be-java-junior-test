abstract class Vehicle {
    private final String brand;

    protected Vehicle(String brand) {
        this.brand = brand;
    }

    public String getBrand() {
        return brand;
    }

    public abstract String move();
}

final class Car extends Vehicle {

    Car(String brand) {
        super(brand);
    }

    @Override
    public String move() {
        return getBrand() + " car is driving";
    }
}

final class Motorcycle extends Vehicle {

    Motorcycle(String brand) {
        super(brand);
    }

    @Override
    public String move() {
        return getBrand() + " motorcycle is riding";
    }
}

final class VehicleDemo {

    private VehicleDemo() {
    }

    public static void main(String[] args) {
        Vehicle[] vehicles = {
                new Car("Toyota"),
                new Motorcycle("Honda")
        };

        for (Vehicle vehicle : vehicles) {
            System.out.println(vehicle.move());
        }
    }
}
