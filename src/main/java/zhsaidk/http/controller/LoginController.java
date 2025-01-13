package zhsaidk.http.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import zhsaidk.database.entity.Role;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String login() {
        return "account/signin";
    }


    @GetMapping("/registration")
    public String register(Model model) {
        model.addAttribute("roles", Role.values());
        return "account/registration";
    }
}
