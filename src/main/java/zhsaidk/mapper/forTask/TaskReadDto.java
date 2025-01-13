package zhsaidk.mapper.forTask;

import org.springframework.stereotype.Component;
import zhsaidk.database.dto.TaskDto;
import zhsaidk.database.entity.Task;
import zhsaidk.mapper.forUser.Mapper;

@Component
public class TaskReadDto implements Mapper<Task, TaskDto> {
    @Override
    public TaskDto map(Task obj) {
        return new TaskDto(
                obj.getId(),
                obj.getMessage()
        );
    }

    @Override
    public TaskDto map(Task from, TaskDto to) {
        return null;
    }
}
