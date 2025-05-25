package progkorny.boatrentalweb01.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import progkorny.boatrentalweb01.model.Boat;
import progkorny.boatrentalweb01.repository.BoatRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BoatService {

    @Autowired
    private BoatRepository boatRepository;

    public List<Boat> getAllBoats() {
        return boatRepository.findAll();
    }

    public Optional<Boat> getBoatById(Long id) {
        return boatRepository.findById(id);
    }

    public Boat createBoat(Boat boat) {
        return boatRepository.save(boat);
    }

    @Transactional
    public Boat updateBoat(Long id, Boat updatedBoat) {
        Boat existing = boatRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Boat not found"));

        // Optimista zárolás automatikusan működik a @Version mező miatt
        existing.setName(updatedBoat.getName());
        existing.setBrand(updatedBoat.getBrand());
        existing.setLength(updatedBoat.getLength());
        existing.setModel(updatedBoat.getModel());
        existing.setBuildYear(updatedBoat.getBuildYear());
        existing.setDailyRate(updatedBoat.getDailyRate());
        existing.setAvailable(updatedBoat.isAvailable());
        existing.setNumberOfSeats(updatedBoat.getNumberOfSeats());
        existing.setVersion(updatedBoat.getVersion());

        return boatRepository.save(existing);
    }

    public boolean deleteBoat(Long id) {
        if (boatRepository.existsById(id)) {
            boatRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
