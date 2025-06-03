package progkorny.boatrentalweb01.service;

import progkorny.boatrentalweb01.model.Boat;
import progkorny.boatrentalweb01.model.Customer;
import progkorny.boatrentalweb01.model.RentalEvent;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service  // Ez az annotáció jelzi, hogy ez egy Spring service komponens
public class RentalEventService {


    // RentalEvent lista, egyetlen elem példányosítva builder patternnel
    private List<RentalEvent> rentalEvents = new ArrayList<RentalEvent>(List.of(
            RentalEvent.builder()
                    .id(1)                            // Az esemény azonosítója
                    .boat(new Boat())                 // Új hajó objektum (üres példány)
                    .customer(new Customer())         // Új ügyfél objektum (üres példány)
                    .rentalDate(LocalDate.of(2025, 3, 18))  // Bérlés kezdete
                    .isClosed(true)                          // Bérlés lezárva
                    .totalCost(8000)                        // Fizetett összeg
                    .returnDate(LocalDate.of(2025, 3, 20)) // Visszahozás dátuma
                    .build()));

    // Visszaadja az összes tárolt RentalEvent-et
    public List<RentalEvent> getAllRentalEvents() {
        return rentalEvents;
    }
}
