package pl.coderslab.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "games")
public class Game{
    //Game long id, long user_id, int level, long moves, long time;
    //games: id BIGINT(20), user_id BIGINT(20), level INT(1), moves BIGINT(20), time BIGINT(20)

    private long id;
    private long user_id;
    private int level;
    private long moves;
    private long time;

    public Game(){}

    public Game(long user_id, int level, long moves, long time){
        this.user_id = user_id;
        this.level = level;
        this.moves = moves;
        this.time = time;
    }

    @ManyToOne
    private User user;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public long getUser_id() {
        return user_id;
    }
    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }

    public long getMoves() {
        return moves;
    }
    public void setMoves(long moves) {
        this.moves = moves;
    }

    public long getTime() {
        return time;
    }
    public void setTime(long time) {
        this.time = time;
    }
}
