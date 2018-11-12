package pl.coderslab.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.coderslab.entity.Game;
import pl.coderslab.repository.GameRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Component
@Transactional
public class GameDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    GameRepository gameRepository;

    public void saveToDb(Game game){
        entityManager.persist(game);
    }

    public Integer countAllGamesByUserId(long user_id){
        return gameRepository.countAllGamesByUserId(user_id);
    }

    public Long countAllMovesByUserId(long user_id){
        return gameRepository.countAllMovesByUserId(user_id);
    }

    public Long countAllTimeByUserId(long user_id){
        return gameRepository.countAllTimeByUserId(user_id);
    }

    public List<Game> load10BestMovesByUserIdOnLevel(long user_id, int level){
        return gameRepository.load10BestMovesByUserIdOnLevel(user_id, level);
    }

    public List<Game> load10BestTimeByUserIdOnLevel(long user_id, int level){
        return gameRepository.load10BestTimeByUserIdOnLevel(user_id, level);
    }
}
