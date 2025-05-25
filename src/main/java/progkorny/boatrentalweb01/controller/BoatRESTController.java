package progkorny.boatrentalweb01.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.dao.OptimisticLockingFailureException;
import progkorny.boatrentalweb01.model.Boat;
import progkorny.boatrentalweb01.service.BoatService;

import java.util.List;

@RestController
@RequestMapping("/api/boats")
public class BoatRESTController {

    @Autowired
    private BoatService boatService;

    @GetMapping
    public List<Boat> getAllBoats() {
        return boatService.getAllBoats();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Boat> getBoatById(@PathVariable Long id) {
        return boatService.getBoatById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Boat createBoat(@RequestBody Boat boat) {
        return boatService.createBoat(boat);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBoat(@PathVariable Long id, @RequestBody Boat boat) {
        try {
            Boat updated = boatService.updateBoat(id, boat);
            return ResponseEntity.ok(updated);
        } catch (OptimisticLockingFailureException e) {
            return ResponseEntity.status(409).body("Hiba: A hajót időközben más is módosította.");
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBoat(@PathVariable Long id) {
        if (boatService.deleteBoat(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
