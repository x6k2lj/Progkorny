package progkorny.boatrentalweb01.service;

import progkorny.boatrentalweb01.exception.NosuchEntityException;
import progkorny.boatrentalweb01.model.Boat;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service  // Service réteg komponens, a Spring automatikusan kezeli az életciklust
public class BoatServiceWithArrayList {

    // Hajók listája, inicializálva 3 előre definiált hajóval
    private List<Boat> boats = new ArrayList<>(List.of(
            createBoat(1L, "Fregatt", 1992.0),
            createBoat(2L, "Szélvész", 1984.0),
            createBoat(3L, "Hadi", 1989.0)
    ));

    // Segédfüggvény hajó létrehozására adott id, név és hossz alapján
    private Boat createBoat(Long id, String name, double length) {
        Boat boat = new Boat();
        boat.setId(id);
        boat.setName(name);
        boat.setLength(length);
        // További alapértelmezett értékek beállítása
        boat.setBrand("Ismeretlen");
        boat.setModel("Alap");
        boat.setBuildYear(2000);
        boat.setDailyRate(100.0);
        boat.setAvailable(true);
        boat.setNumberOfSeats(4);
        return boat;
    }

    // Visszaadja az összes hajót listában
    public List<Boat> getAllBoat() {
        return boats;
    }

    // Hajó keresése ID alapján, ha nincs ilyen hajó, kivételt dob
    public Boat getBoatById(Long id) {
        return boats.stream()
                .filter(boat -> boat.getId().equals(id))  // Szűrés azonosító alapján
                .findFirst()                              // Az első találat
                .orElseThrow(() ->                        // Ha nincs találat, kivétel
                        new NosuchEntityException("There is no Boat with id: " + id));
    }

    // Beszúr egy új hajót vagy frissíti a meglévőt azonosító alapján
    public int insertOrUpdateBoat(Boat boat) {
        // Először törli, ha már van ilyen ID-jű hajó a listában
        boats.removeIf(b -> b.getId().equals(boat.getId()));
        // Majd hozzáadja az új vagy frissített hajót
        boats.add(boat);
        // Visszatér a listában lévő hajók számával
        return boats.size();
    }

    // Hajó törlése ID alapján
    public boolean deleteBoatById(Long id) {
        Optional<Boat> boatToDelete = boats.stream()
                .filter(b -> b.getId().equals(id))
                .findFirst();

        if (boatToDelete.isPresent()) {
            boats.remove(boatToDelete.get());  // Ha megtalálta, törli
            return true;
        } else {
            // Ha nem található a hajó, kivételt dob
            throw new NosuchEntityException("There is no Boat to delete with id: " + id);
        }
    }
}
