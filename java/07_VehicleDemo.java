abstract class Vehicle {
    private final String licensePlate;
    private int maxSpeed;

    protected Vehicle(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    protected final void updateMaxSpeed(int speed) {
        this.maxSpeed = speed;
    }

    protected final int currentMaxSpeed() {
        return maxSpeed;
    }

    public abstract void setMaxSpeed(int speed);

    public abstract int getMaxSpeed();

    public abstract double calculateFuelConsumption(int distance);

    public void displayInfo() {
        System.out.println("License Plate: " + licensePlate + ", Max Speed: " + getMaxSpeed());
    }
}

class Car extends Vehicle {
    private int fuelEfficiency;

    Car(String licensePlate, int fuelEfficiency) {
        super(licensePlate);
        this.fuelEfficiency = fuelEfficiency;
    }

    @Override
    public void setMaxSpeed(int speed) {
        updateMaxSpeed(speed);
    }

    @Override
    public int getMaxSpeed() {
        return currentMaxSpeed();
    }

    @Override
    public double calculateFuelConsumption(int distance) {
        return (double) distance / fuelEfficiency;
    }

    public int getFuelEfficiency() {
        return fuelEfficiency;
    }

    public void setFuelEfficiency(int fuelEfficiency) {
        this.fuelEfficiency = fuelEfficiency;
    }
}

class Truck extends Vehicle {
    private int fuelEfficiency;
    private int cargoWeight;

    Truck(String licensePlate, int fuelEfficiency, int cargoWeight) {
        super(licensePlate);
        this.fuelEfficiency = fuelEfficiency;
        this.cargoWeight = cargoWeight;
    }

    @Override
    public void setMaxSpeed(int speed) {
        updateMaxSpeed(speed);
    }

    @Override
    public int getMaxSpeed() {
        return currentMaxSpeed();
    }

    @Override
    public double calculateFuelConsumption(int distance) {
        return ((double) distance / fuelEfficiency) + (cargoWeight * 0.05);
    }

    public int getFuelEfficiency() {
        return fuelEfficiency;
    }

    public void setFuelEfficiency(int fuelEfficiency) {
        this.fuelEfficiency = fuelEfficiency;
    }

    public int getCargoWeight() {
        return cargoWeight;
    }

    public void setCargoWeight(int cargoWeight) {
        this.cargoWeight = cargoWeight;
    }
}
