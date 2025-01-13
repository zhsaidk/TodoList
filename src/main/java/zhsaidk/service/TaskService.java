package zhsaidk.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import zhsaidk.database.dto.TaskDto;
import zhsaidk.database.entity.Task;
import zhsaidk.database.entity.User;
import zhsaidk.database.repository.TaskRepository;
import zhsaidk.database.repository.UserRepository;
import zhsaidk.mapper.forUser.TaskReadMapper;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final TaskReadMapper taskReadMapper;

    public List<TaskDto> findUserTasks(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        return taskRepository.findTaskByUserId(user.getId())
                .stream().map(taskReadMapper::map)
                .toList();
    }

    @Transactional
    public boolean deleteUserTasks(Long id) {
        return taskRepository.findById(id)
                    .map(task -> {
                        taskRepository.deleteById(id);
                        return true;
                    })
                    .orElseThrow(()->new IllegalArgumentException("Task not found"));
    }

    @Transactional
    public void deleteAllUserTasks(String username) {
        User user = userRepository.findByPrincipalUsername(username)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
        taskRepository.deleteTaskByUser_Id(user.getId());
    }



//    public TaskDto findById(Long id){
//        return taskRepository.findById(id)
//                .map(taskReadMapper::map)
//                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
//    }

    @Transactional
    public void createTask(String message, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        Task task = new Task();
        task.setMessage(message);
        task.setUser(user);
        taskRepository.save(task);
    }

    public List<TaskDto> findTaskContainingWord(String search, String username){
        User user = userRepository.findByPrincipalUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return taskRepository.findTaskContainingWord(user.getId(), search)
                .stream().map(taskReadMapper::map).toList();
    }
}
