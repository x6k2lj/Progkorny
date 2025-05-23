package progkorny.boatrentalweb01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/")
    public String index() {
        return "index"; // templates/index.html
    }

    @GetMapping("/delete")
    public String deleteBoatPage() {
        return "delete-boat"; // templates/delete-boat.html
    }

    @GetMapping("/upload")
    public String uploadBoatPage() {
        return "upload-boat"; // templates/upload-boat.html
    }

    @GetMapping("/upload-renter")
    public String uploadCustomerPage() {
        return "upload-renter"; // templates/upload-renter.html
    }
}
