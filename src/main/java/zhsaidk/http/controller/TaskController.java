package zhsaidk.http.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import zhsaidk.database.dto.TaskDto;
import zhsaidk.database.entity.Task;
import zhsaidk.database.repository.TaskRepository;
import zhsaidk.database.repository.UserRepository;
import zhsaidk.mapper.forUser.TaskReadMapper;
import zhsaidk.service.TaskService;
import zhsaidk.service.UserService;

import java.security.Principal;

@Controller
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;
    private final UserService userService;

    @GetMapping
    public String tasks(Model model,
                        Principal principal,
                        @RequestParam(required = false) String search){
        if (search != null) {
            model.addAttribute("tasks", taskService.findTaskContainingWord(search, principal.getName()));
        }
        else{
            model.addAttribute("tasks", taskService.findUserTasks(principal.getName()));
        }
        return "task/task";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        if (!taskService.deleteUserTasks(id)){
            return "redirect:/tasks/" + id;
        }
        return "redirect:/tasks";
    }

    @PostMapping("/clear")
    public String deleteAll(Principal principal){
        taskService.deleteAllUserTasks(principal.getName());
        return "redirect:/tasks";
    }


    @PostMapping("/create")
    public String create(@Param("message") String message, Principal principal){
        taskService.createTask(message, principal.getName());
        return "redirect:/tasks";
    }
}
