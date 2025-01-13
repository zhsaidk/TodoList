package zhsaidk.mapper.forUser;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import zhsaidk.database.dto.UserDto;
import zhsaidk.database.entity.User;

@Component
@RequiredArgsConstructor
public class UserReadMapper implements Mapper<User, UserDto>{
    private final TaskReadMapper taskReadMapper;
    @Override
    public UserDto map(User from) {
        return new UserDto(
                from.getId(),
                from.getFirstName(),
                from.getLastName(),
                from.getUsername(),
                from.getBirthDate(),
                from.getRole(),
                from.getTasks().stream()
                        .map(taskReadMapper::map)
                        .toList() // Для избежание циклической зависимости
        );
    }

    @Override
    public UserDto map(User from, UserDto to) {
        return null;
    }
}
