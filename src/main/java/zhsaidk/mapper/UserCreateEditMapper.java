package zhsaidk.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import zhsaidk.database.dto.UserCreateEditDto;
import zhsaidk.database.entity.User;

@Component
@RequiredArgsConstructor
public class UserCreateEditMapper implements Mapper<UserCreateEditDto, User>{
    private final PasswordEncoder passwordEncoder;

    @Override
    public User map(UserCreateEditDto userCreateEditDto) {
        User user = new User();
        user.setFirstName(userCreateEditDto.getFirstName());
        user.setLastName(userCreateEditDto.getLastName());
        user.setUsername(userCreateEditDto.getUsername());
        user.setBirthDate(userCreateEditDto.getBirthDate());
        user.setPassword(passwordEncoder.encode(userCreateEditDto.getPassword()));
        user.setRole(userCreateEditDto.getRole());
        return user;
    }

    @Override
    public User map(UserCreateEditDto from, User to) {
        to.setFirstName(from.getFirstName());
        to.setLastName(from.getLastName());
        to.setUsername(from.getUsername());
        to.setBirthDate(from.getBirthDate());
        to.setRole(from.getRole());
        return to;
    }
}
