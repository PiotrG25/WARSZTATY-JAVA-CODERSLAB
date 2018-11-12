package pl.coderslab.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByName(String name);

    @Query("select u.id from User u where u.name = ?1")
    long returnIdWhenNameFound(String name);

    @Query("select u.id from User u where u.email = ?1")
    long returnIdWhenEmailFound(String email);
}
