package progkorny.boatrentalweb01.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Boat {

    @Id private int id;
    private String brand;
    private String model;
    private int buildYear;
    private String licensePlate;
    private double rentalPricePerDay;
    private boolean available;
    private int numberOfSeats;

    public Boat() {}



    private Boat(Builder builder) {
        this.id = builder.id;
        this.brand = builder.brand;
        this.model = builder.model;
        this.buildYear = builder.buildYear;
        this.licensePlate = builder.licensePlate;
        this.rentalPricePerDay = builder.rentalPricePerDay;
        this.available = builder.available;
        this.numberOfSeats = builder.numberOfSeats;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private int id;
        private String brand;
        private String model;
        private int buildYear;
        private String licensePlate;
        private double rentalPricePerDay;
        private boolean available;
        private int numberOfSeats;

        // private Builder() {}

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder brand(String brand) {
            this.brand = brand;
            return this;
        }

        public Builder model(String model) {
            this.model = model;
            return this;
        }

        public Builder buildYear(int buildYear) {
            this.buildYear = buildYear;
            return this;
        }

        public Builder licensePlate(String licensePlate) {
            this.licensePlate = licensePlate;
            return this;
        }

        public Builder rentalPricePerDay(double rentalPricePerDay) {
            this.rentalPricePerDay = rentalPricePerDay;
            return this;
        }

        public Builder available(boolean available) {
            this.available = available;
            return this;
        }

        public Builder numberOfSeats(int numberOfSeats) {
            this.numberOfSeats = numberOfSeats;
            return this;
        }

        public Boat build() {
            return new Boat(this);
        }
    }

    @Override
    public String toString() {
        return "Boat{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", buildYear=" + buildYear +
                ", licensePlate='" + licensePlate + '\'' +
                ", rentalPricePerDay=" + rentalPricePerDay +
                ", available=" + available +
                ", numberOfSeats=" + numberOfSeats +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getBuildYear() {
        return buildYear;
    }

    public void setBuildYear(int buildYear) {
        this.buildYear = buildYear;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public double getRentalPricePerDay() {
        return rentalPricePerDay;
    }

    public void setRentalPricePerDay(double rentalPricePerDay) {
        this.rentalPricePerDay = rentalPricePerDay;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }
}