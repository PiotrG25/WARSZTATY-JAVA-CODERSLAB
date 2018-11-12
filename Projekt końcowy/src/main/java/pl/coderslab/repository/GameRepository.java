package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.entity.Game;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Long> {

    @Query(value = "SELECT COUNT(*) FROM games WHERE user_id = ?1", nativeQuery = true)
//    @Query("select count(*) from Game g where g.id = ?1")
    int countAllGamesByUserId(long user_id);

    @Query("select sum(g.moves) from Game g where g.user_id = ?1")
    long countAllMovesByUserId(long user_id);

    @Query("select sum(g.time) from Game g where g.user_id = ?1")
    long countAllTimeByUserId(long user_id);

    @Query(value = "SELECT * FROM games WHERE user_id = ?1 AND level = ?2 ORDER BY moves ASC LIMIT 10", nativeQuery = true)
    List<Game> load10BestMovesByUserIdOnLevel(long user_id, int level);

    @Query(value = "SELECT * FROM games WHERE user_id = ?1 AND level = ?2 ORDER BY time ASC LIMIT 10", nativeQuery = true)
    List<Game> load10BestTimeByUserIdOnLevel(long user_id, int level);
}
