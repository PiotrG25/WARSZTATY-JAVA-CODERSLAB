package spring.beans;

import java.awt.image.DataBufferUShort;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class Game{
    //Game long id, long user_id, int level, int moves, long time;
    //games: id BIGINT(20), user_id BIGINT(20), level INT(1), moves INT(10), time BIGINT(20)

    private long id;
    private long user_id;
    private int level;
    private int moves;
    private long time;

    public Game(long user_id, int level, int moves, long time){
        this.user_id = user_id;
        this.level = level;
        this.moves = moves;
        this.time = time;
    }

    public static List<Game> loadAllGamesByUser(Connection conn, User user)throws SQLException{
        String select = "SELECT * FROM games WHERE user_id = ?;";
        PreparedStatement pstm = conn.prepareStatement(select);
        pstm.setLong(1, user.getId());
        List<Game> games = new ArrayList<>();

        ResultSet rs = pstm.executeQuery();
        while(rs.next()){
            Game game = new Game(rs.getLong(2), rs.getInt(3), rs.getInt(4), rs.getLong(5));
            game.setId(rs.getLong(1));
            games.add(game);
        }
        return games;
    }

    public void saveToDb(Connection conn)throws SQLException{
        String insert = "INSERT INTO games (user_id, level, moves, time) VALUES (?, ?, ?, ?);";
        PreparedStatement pstm = conn.prepareStatement(insert, RETURN_GENERATED_KEYS);

        pstm.setLong(1, user_id);
        pstm.setInt(2, level);
        pstm.setInt(3, moves);
        pstm.setLong(4, time);

        pstm.executeUpdate();
        ResultSet rs = pstm.getGeneratedKeys();
        rs.next();
        this.id = rs.getInt(1);
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
