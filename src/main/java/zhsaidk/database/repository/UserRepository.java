package zhsaidk.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import zhsaidk.database.entity.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
//    @Query("select u from User u")
    List<User> findAll();
}
