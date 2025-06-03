package progkorny.boatrentalweb01.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.dao.OptimisticLockingFailureException;
import progkorny.boatrentalweb01.model.Boat;
import progkorny.boatrentalweb01.service.BoatService;

import java.util.List;
// Ez az osztály egy REST API vezérlő (controller), amely a hajók kezeléséért felel.
// Az URL-ek a "/api/boats" útvonalról érhetők el.
@RestController
@RequestMapping("/api/boats")
public class BoatRESTController {
    // A hajókkal kapcsolatos üzleti logikát egy külön szolgáltatás (service) kezeli.
    // Az @Autowired automatikusan betölti a BoatService példányt a Spring konténerből.
    @Autowired
    private BoatService boatService;
    // GET /api/boats
    // Minden hajó lekérdezése.
    @GetMapping
    public List<Boat> getAllBoats() {
        return boatService.getAllBoats();
    }
    // GET /api/boats/{id}
    // Egy adott hajó lekérdezése azonosító alapján.
    // Ha nem található, 404 NOT FOUND válasszal tér vissza.
    @GetMapping("/{id}")
    public ResponseEntity<Boat> getBoatById(@PathVariable Long id) {
        return boatService.getBoatById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    // POST /api/boats
    // Új hajó létrehozása. A kérés törzsében (body) JSON formátumban küldjük a hajó adatait.
    @PostMapping
    public Boat createBoat(@RequestBody Boat boat) {
        return boatService.createBoat(boat);
    }
    // PUT /api/boats/{id}
    // Egy meglévő hajó frissítése azonosító alapján.
    // Optimista zárolás esetén 409 Conflict hibaüzenettel tér vissza.
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
    // DELETE /api/boats/{id}
    // Hajó törlése azonosító alapján.
    // Ha sikeres → 204 No Content, ha nem található → 404 Not Found
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBoat(@PathVariable Long id) {
        if (boatService.deleteBoat(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
