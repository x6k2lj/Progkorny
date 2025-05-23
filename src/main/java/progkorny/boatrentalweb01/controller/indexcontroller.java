package progkorny.boatrentalweb01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class indexcontroller {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("message", "Üdvözlünk a hajóbérlő oldalon!");
        return "index";
    }
}
