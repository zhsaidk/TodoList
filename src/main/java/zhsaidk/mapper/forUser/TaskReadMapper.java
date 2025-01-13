package zhsaidk.mapper.forUser;

import org.springframework.stereotype.Component;
import zhsaidk.database.dto.TaskDto;
import zhsaidk.database.entity.Task;

@Component
public class TaskReadMapper implements Mapper<Task, TaskDto>{
    @Override
    public TaskDto map(Task from) {
        return new TaskDto(
                from.getId(),
                from.getMessage()
        );
    }

    @Override
    public TaskDto map(Task from, TaskDto to) {
        return null;
    }
}
