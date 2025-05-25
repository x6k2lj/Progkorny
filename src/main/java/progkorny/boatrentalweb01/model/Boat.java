package progkorny.boatrentalweb01.model;

import jakarta.persistence.*;

@Entity
@Table(name = "boat")
public class Boat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String brand;

    @Column(nullable = false)
    private double length;

    private String model;

    @Column(name = "build_year", nullable = false)
    private int buildYear;

    @Column(name = "daily_rate", nullable = false)
    private double dailyRate;

    @Column(nullable = false)
    private boolean available;

    @Column(name = "number_of_seats", nullable = false)
    private int numberOfSeats;

   @Version
   private Integer version;

    // Alapértelmezett konstruktor
    public Boat() {
    }

    // Teljes konstruktor
    public Boat(String name, String brand, double length, String model, int buildYear,
                double dailyRate, boolean available, int numberOfSeats) {
        this.name = name;
        this.brand = brand;
        this.length = length;
        this.model = model;
        this.buildYear = buildYear;
        this.dailyRate = dailyRate;
        this.available = available;
        this.numberOfSeats = numberOfSeats;
    }

    // Getterek
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    public double getLength() {
        return length;
    }

    public String getModel() {
        return model;
    }

    public int getBuildYear() {
        return buildYear;
    }

    public double getDailyRate() {
        return dailyRate;
    }

    public boolean isAvailable() {
        return available;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public Integer getVersion() {
       return version;
    }

    // Setterek
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setBuildYear(int buildYear) {
        this.buildYear = buildYear;
    }

    public void setDailyRate(double dailyRate) {
        this.dailyRate = dailyRate;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
