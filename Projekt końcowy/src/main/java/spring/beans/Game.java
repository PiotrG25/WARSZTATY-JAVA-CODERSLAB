package spring.beans;

import java.sql.Connection;
import java.util.List;

public class Game{
    //Game long id, long user_id, int level, int moves, long time;
    //games: id BIGINT(20), user_id BIGINT(20), level INT(1), moves INT(10), time BIGINT(20)

    private long id;
    private long user_id;
    private int level;
    private int moves;
    private long time;

    public Game(long user_id, int level, int moves, long time) {
        this.user_id = user_id;
        this.level = level;
        this.moves = moves;
        this.time = time;
    }

    public static List<Game> loadAllGamesByUser(Connection conn, User user){
        String select = "SELECT * FROM games WHERE user_id = ?";
        //todo
        //todo
        //todo
        return null;
    }

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

    public int getMoves() {
        return moves;
    }
    public void setMoves(int moves) {
        this.moves = moves;
    }

    public long getTime() {
        return time;
    }
    public void setTime(long time) {
        this.time = time;
    }
}
