package zhsaidk.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import zhsaidk.database.entity.Task;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    @Modifying
    @Query("delete from Task t where t.user.id = :userId")
    void deleteTaskByUser_Id(Long userId);

    @Query("select t from Task t where t.user.id = :id and t.message like %:value%")
    List<Task> findTaskContainingWord(Long id, String value);

    @Query("select t from Task t where t.user.id = :userId")
    List<Task> findTaskByUserId(Long userId);
}
