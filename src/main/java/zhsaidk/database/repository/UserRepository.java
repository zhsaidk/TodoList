package zhsaidk.database.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import zhsaidk.database.entity.Task;
import zhsaidk.database.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
//    @EntityGraph(attributePaths = {"tasks"})
    List<User> findAll();


    Optional<User> findByUsername(String username);

    @Query("select u from User u left join fetch u.tasks where u.id = :id")
    Optional<User> findById(Long id); //Если нцжны только записи у которых есть задачи, просто join fetch, если нужны все join fetch

    @Query("select u from User u left join fetch u.tasks where u.username = :username")
    Optional<User> findByPrincipalUsername(String username);
}
