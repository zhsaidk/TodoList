package zhsaidk.database.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"firstName", "lastName"}
        )
)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 64)
    private String firstName;

    @Column(length = 64)
    private String lastName;

    private LocalDate birthDate;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)// Удалить связанные задачи
    private List<Task> tasks;
}
