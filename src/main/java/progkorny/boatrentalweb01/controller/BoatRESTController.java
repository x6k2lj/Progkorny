package progkorny.boatrentalweb01.controller;


import progkorny.boatrentalweb01.model.Boat;
import progkorny.boatrentalweb01.service.BoatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("api/Boat")
public class BoatRESTController {

    @Autowired
    private BoatService BoatService;

    @GetMapping()
    public List<Boat> getAllBoats()
    {return BoatService.getAllBoat(); }

    @GetMapping("/{id}")
    public Boat getBoatById(@PathVariable int id) {
        return  BoatService.getBoatById(id);
    }

    @PostMapping()
    public Integer putBoatIntoDb(@RequestBody Boat Boat) {
        int answer = BoatService.insertOrUpdateBoat(Boat);
        return answer;
    }

    @DeleteMapping("/{id}")
    public String deleteBoatById( @PathVariable int id) {
        if(BoatService.deleteBoatById(id)) return "Boat with id " +id+ " is deleted"; else return "nothing is deleted";
    }

}

