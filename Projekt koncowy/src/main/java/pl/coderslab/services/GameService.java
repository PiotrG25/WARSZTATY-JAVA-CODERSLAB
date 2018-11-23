package pl.coderslab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.entity.Game;
import pl.coderslab.repository.GameRepository;

import java.util.List;

@Service
public class GameService {

    @Autowired
    GameRepository gameRepository;

    public Integer countAllGamesByUserId(Long userId){
        return gameRepository.countAllGamesByUserId(userId);
    }

    public Long countAllMovesByUserId(Long userId){
        return gameRepository.countAllMovesByUserId(userId);
    }

    public Long countAllTimeByUserId(Long userId){
        return gameRepository.countAllTimeByUserId(userId);
    }

    public List<Game> load10BestMovesByUserIdOnLevel(Long userId, Integer level){
        return gameRepository.load10BestMovesByUserIdOnLevel(userId, level);
    }

    public List<Game> load10BestTimeByUserIdOnLevel(Long userId, Integer level){
        return gameRepository.load10BestTimeByUserIdOnLevel(userId, level);
    }
}
