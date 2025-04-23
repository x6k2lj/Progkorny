package progkorny.boatrentalweb01.service;

import progkorny.boatrentalweb01.exception.NosuchEntityException;
import progkorny.boatrentalweb01.model.Boat;
import progkorny.boatrentalweb01.repository.BoatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BoatService {
    @Autowired
    private BoatRepository BoatRepository;
    private List<Boat> boats = new ArrayList<Boat>(List.of( Boat.builder()
                    .id(1)
                    .available(true)
                    .licensePlate("EEF257")
                    .brand("Ford")
                    .model("Escort")
                    .buildYear(1992)
                    .numberOfSeats(5)
                    .rentalPricePerDay(4000)
                    .build()
            ,
            Boat.builder()
                    .id(2)
                    .available(true)
                    .licensePlate("ABV016")
                    .brand("Trabant")
                    .model("1.1")
                    .buildYear(1984)
                    .numberOfSeats(4)
                    .rentalPricePerDay(4000)
                    .build()
            ,
            Boat.builder()
                    .id(3)
                    .available(false)
                    .licensePlate("DSH906")
                    .brand("Skoda")
                    .model("Favorit")
                    .buildYear(1989)
                    .numberOfSeats(5)
                    .rentalPricePerDay(4000)
                    .build()
    ));

    public List<Boat> getAllBoat() {
        return BoatRepository.findAll();
    }

    public Boat getBoatById(int id) {

        Optional<Boat> optionalBoat = BoatRepository.findById(id);
        if (optionalBoat.isPresent()) {
            return optionalBoat.get();
        }
        else throw new NosuchEntityException("There is no Boat with id:" + id);
    }

    public int insertOrUpdateBoat(progkorny.boatrentalweb01.model.Boat boat) {
        BoatRepository.save(boat);
        BoatRepository.flush();
        return (int) BoatRepository.count();
    }

    public boolean deleteBoatById(int id) {
        Optional<progkorny.boatrentalweb01.model.Boat> boatToDelete =  BoatRepository.findById(id);
        if (boatToDelete.isPresent()) {
            BoatRepository.deleteBoatById(id);
            return true;
        }
        else throw new NosuchEntityException("There is no Boat to delete with id:" + id);

    }


}

