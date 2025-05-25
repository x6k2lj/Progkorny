package progkorny.boatrentalweb01.service;

import progkorny.boatrentalweb01.exception.NosuchEntityException;
import progkorny.boatrentalweb01.model.Boat;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BoatServiceWithArrayList {

    private List<Boat> boats = new ArrayList<>(List.of(
            createBoat(1L, "Fregatt", 1992.0),
            createBoat(2L, "Szélvész", 1984.0),
            createBoat(3L, "Hadi", 1989.0)
    ));

    private Boat createBoat(Long id, String name, double length) {
        Boat boat = new Boat();
        boat.setId(id);
        boat.setName(name);
        boat.setLength(length);
        // További mezőket is állíthatsz itt, ha szükséges:
        boat.setBrand("Ismeretlen");
        boat.setModel("Alap");
        boat.setBuildYear(2000);
        boat.setDailyRate(100.0);
        boat.setAvailable(true);
        boat.setNumberOfSeats(4);
        return boat;
    }

    public List<Boat> getAllBoat() {
        return boats;
    }

    public Boat getBoatById(Long id) {
        return boats.stream()
                .filter(boat -> boat.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NosuchEntityException("There is no Boat with id: " + id));
    }

    public int insertOrUpdateBoat(Boat boat) {
        boats.removeIf(b -> b.getId().equals(boat.getId()));
        boats.add(boat);
        return boats.size();
    }

    public boolean deleteBoatById(Long id) {
        Optional<Boat> boatToDelete = boats.stream()
                .filter(b -> b.getId().equals(id))
                .findFirst();

        if (boatToDelete.isPresent()) {
            boats.remove(boatToDelete.get());
            return true;
        } else {
            throw new NosuchEntityException("There is no Boat to delete with id: " + id);
        }
    }
}
