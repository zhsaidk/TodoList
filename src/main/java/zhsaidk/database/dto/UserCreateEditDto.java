package zhsaidk.database.dto;

import lombok.Value;
import zhsaidk.database.entity.Role;

import java.time.LocalDate;

@Value
public class UserCreateEditDto {
    String firstName;
    String lastName;
    String username;
    LocalDate birthDate;
    String password;
    Role role;
}
