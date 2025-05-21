package progkorny.boatrentalweb01.service;
import org.springframework.web.bind.annotation.CrossOrigin;
import progkorny.boatrentalweb01.exception.NosuchEntityException;
import progkorny.boatrentalweb01.model.Boat;
import progkorny.boatrentalweb01.repository.BoatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@CrossOrigin(origins = "*")


@Service
public class BoatService {

    @Autowired
    private BoatRepository boatRepository;

    public List<Boat> getAllBoat() {
        return boatRepository.findAll();
    }

    public Boat getBoatById(Long id) {
        return boatRepository.findById(id)
                .orElseThrow(() -> new NosuchEntityException("There is no Boat with id: " + id));
    }

    public Long insertOrUpdateBoat(Boat boat) {
        Boat savedBoat = boatRepository.save(boat);
        return savedBoat.getId();
    }

    public boolean deleteBoatById(Long id) {
        if (boatRepository.existsById(id)) {
            boatRepository.deleteById(id);
            return true;
        } else {
            throw new NosuchEntityException("There is no Boat to delete with id: " + id);
        }
    }
}
