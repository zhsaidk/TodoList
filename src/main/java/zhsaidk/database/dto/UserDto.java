package zhsaidk.database.dto;

import lombok.Value;
import zhsaidk.database.entity.Role;
import zhsaidk.database.entity.Task;

import java.time.LocalDate;
import java.util.List;

@Value
public class UserDto {
    Long id;
    String firstName;
    String lastName;
    String username;
    LocalDate birthDate;
    Role role;
    List<TaskDto> tasks;
}
