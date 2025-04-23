package progkorny.boatrentalweb01.service;

import progkorny.boatrentalweb01.exception.NosuchEntityException;
import progkorny.boatrentalweb01.model.Boat;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BoatServiceWithArrayList {
    private List<Boat> Boats = new ArrayList<Boat>(List.of(
            Boat.builder()
                    .id(1)
                    .available(true)
                    .licensePlate("tal001")
                    .brand("Fregatt")
                    .model("Long")
                    .buildYear(1992)
                    .numberOfSeats(5)
                    .rentalPricePerDay(4000)
                    .build()
            ,
            Boat.builder()
                    .id(2)
                    .available(true)
                    .licensePlate("ABV016")
                    .brand("Szélvész")
                    .model("long")
                    .buildYear(1984)
                    .numberOfSeats(4)
                    .rentalPricePerDay(4000)
                    .build()
            ,
            Boat.builder()
                    .id(3)
                    .available(false)
                    .licensePlate("DSH906")
                    .brand("Hadi")
                    .model("Dandár")
                    .buildYear(1989)
                    .numberOfSeats(5)
                    .rentalPricePerDay(4000)
                    .build()
    ));

    public List<Boat> getAllBoat() {
        return Boats;
    }

    public Boat getBoatById(int id) {
        Optional<Boat> optionalBoat = Boats.stream()
                        .filter(Boat -> Boat.getId() == id)
                        .findFirst();
        if (optionalBoat.isPresent()) {
            return optionalBoat.get();
        }
        else throw new NosuchEntityException("There is no Boat with id:" + id);
    }

    public int insertOrUpdateBoat(Boat boat) {
        int id = boat.getId();
        if (Boats.stream()
                .anyMatch(c->(c.getId()==id))
                ) {
            Boats.set(id, boat);
        }
        else { Boats.add(boat);}
        return Boats.size();
    }

}

