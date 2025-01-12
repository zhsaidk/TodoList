package zhsaidk.http.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import zhsaidk.database.dto.UserCreateEditDto;
import zhsaidk.database.entity.Role;
import zhsaidk.service.UserService;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("users", userService.findAllUsers());
        return "user/users";
    }

    @GetMapping("/{id}")
    public String findById(Model model,
                           @PathVariable("id") Long id) {
        model.addAttribute("user", userService.findById(id));
        model.addAttribute("roles", Role.values());
        return "user/user";
    }

    @GetMapping("/registration")
    public String register(Model model) {
        model.addAttribute("roles", Role.values());
        return "user/registration";
    }

    @PostMapping("/create")
    public String create(UserCreateEditDto userCreateEditDto) {
        return "redirect:/users/" + userService.createUser(userCreateEditDto).getId();
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") Long id,
                         UserCreateEditDto userCreateEditDto) {
        return "redirect:/users/" + userService.updateUser(id, userCreateEditDto).getId();
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        if(userService.deleteUser(id)) {
            return "redirect:/users";
        }
        else {
            throw new RuntimeException("Failed to delete user");
        }
    }
}
