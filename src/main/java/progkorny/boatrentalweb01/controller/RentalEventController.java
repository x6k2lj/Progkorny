package progkorny.boatrentalweb01.controller;

import progkorny.boatrentalweb01.model.RentalEvent;
import progkorny.boatrentalweb01.service.RentalEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("api/rentalevents")
public class RentalEventController {

    @Autowired
    private RentalEventService rentalEventService;

    @GetMapping()
    public List<RentalEvent> getAllRentalEvents()
    {return rentalEventService.getAllRentalEvents(); }

}
