package pl.coderslab.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT * FROM users WHERE name = ?1 AND enabled = 1", nativeQuery = true)
    User findUserByName(String name);

    @Query("select u.id from User u where u.name = ?1 and u.enabled = 1")
    Long returnIdWhenNameFound(String name);

    @Query("select u.id from User u where u.email = ?1 and u.enabled = 1")
    Long returnIdWhenEmailFound(String email);
}
