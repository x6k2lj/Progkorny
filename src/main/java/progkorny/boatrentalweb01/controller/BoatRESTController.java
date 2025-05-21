package progkorny.boatrentalweb01.controller;

import progkorny.boatrentalweb01.model.Boat;
import progkorny.boatrentalweb01.service.BoatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/Boat")
public class BoatRESTController {

    @Autowired
    private BoatService boatService;

    @GetMapping
    public List<Boat> getAllBoats() {
        return boatService.getAllBoat();
    }

    @GetMapping("/{id}")
    public Boat getBoatById(@PathVariable Long id) {
        return boatService.getBoatById(id);
    }

    @PostMapping
    public Long putBoatIntoDb(@RequestBody Boat boat) {
        return boatService.insertOrUpdateBoat(boat);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteBoatById(@PathVariable Long id) {
        if (boatService.deleteBoatById(id)) {
            return "Boat with id " + id + " is deleted";
        } else {
            return "Nothing is deleted";
        }
    }
}
