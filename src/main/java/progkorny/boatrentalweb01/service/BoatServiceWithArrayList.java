package progkorny.boatrentalweb01.service;

import progkorny.boatrentalweb01.exception.NosuchEntityException;
import progkorny.boatrentalweb01.model.Boat;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BoatServiceWithArrayList {

    private List<Boat> Boats = new ArrayList<>(List.of(
            createBoat(1, true, "tal001", "Fregatt", "Long", 1992, 5, 4000),
            createBoat(2, true, "ABV016", "Szélvész", "long", 1984, 4, 4000),
            createBoat(3, false, "DSH906", "Hadi", "Dandár", 1989, 5, 4000)
    ));

    private Boat createBoat(int id, boolean available, String licensePlate, String brand, String model, int buildYear, int numberOfSeats, double rentalPricePerDay) {
        Boat boat = new Boat();
        boat.setId((long) id);
        boat.setAvailable(available);
        boat.setLicensePlate(licensePlate);
        boat.setBrand(brand);
        boat.setModel(model);
        boat.setBuildYear(buildYear);
        boat.setNumberOfSeats(numberOfSeats);
        boat.setRentalPricePerDay(rentalPricePerDay);
        return boat;
    }

    public List<Boat> getAllBoat() {
        return Boats;
    }

    public Boat getBoatById(int id) {
        Optional<Boat> optionalBoat = Boats.stream()
                .filter(boat -> boat.getId() == id)
                .findFirst();
        if (optionalBoat.isPresent()) {
            return optionalBoat.get();
        } else {
            throw new NosuchEntityException("There is no Boat with id:" + id);
        }
    }

    public int insertOrUpdateBoat(Boat boat) {
        Optional<Boat> existingBoat = Boats.stream()
                .filter(b -> b.getId() == boat.getId())
                .findFirst();

        if (existingBoat.isPresent()) {
            Boats.remove(existingBoat.get());
        }
        Boats.add(boat);
        return Boats.size();
    }

    public boolean deleteBoatById(int id) {
        Optional<Boat> boatToDelete = Boats.stream()
                .filter(b -> b.getId() == id)
                .findFirst();

        if (boatToDelete.isPresent()) {
            Boats.remove(boatToDelete.get());
            return true;
        } else {
            throw new NosuchEntityException("There is no Boat to delete with id:" + id);
        }
    }
}
