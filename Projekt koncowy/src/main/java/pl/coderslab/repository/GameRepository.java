package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.entity.Game;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    @Query(value = "SELECT COUNT(*) FROM games WHERE user_id = ?1", nativeQuery = true)
    Integer countAllGamesByUserId(long user_id);

    @Query(value = "SELECT SUM(moves) FROM games WHERE user_id = ?1", nativeQuery = true)
    Long countAllMovesByUserId(long user_id);

    @Query(value = "SELECT SUM(time) FROM games WHERE user_id = ?1", nativeQuery = true)
    Long countAllTimeByUserId(long user_id);

    @Query(value = "SELECT * FROM games WHERE user_id = ?1 AND level = ?2 ORDER BY moves ASC LIMIT 10", nativeQuery = true)
    List<Game> load10BestMovesByUserIdOnLevel(long user_id, int level);

    @Query(value = "SELECT * FROM games WHERE user_id = ?1 AND level = ?2 ORDER BY time ASC LIMIT 10", nativeQuery = true)
    List<Game> load10BestTimeByUserIdOnLevel(long user_id, int level);

}
