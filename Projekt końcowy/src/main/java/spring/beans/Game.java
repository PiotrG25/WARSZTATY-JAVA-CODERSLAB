package spring.beans;

import java.awt.image.DataBufferUShort;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

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

    public static List<Game> loadAllGamesByUser(Connection conn, User user)throws SQLException{
        String select = "SELECT * FROM games WHERE user_id = ?;";
        PreparedStatement pstm = conn.prepareStatement(select);
        pstm.setLong(1, user.getId());
        List<Game> games = new ArrayList<>();

        ResultSet rs = pstm.executeQuery();
        while(rs.next()){
            Game game = new Game(rs.getLong(2), rs.getInt(3), rs.getLong(4), rs.getLong(5));
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
        pstm.setLong(3, moves);
        pstm.setLong(4, time);

        pstm.executeUpdate();
        ResultSet rs = pstm.getGeneratedKeys();
        rs.next();
        this.id = rs.getLong(1);
    }

    public static int countAllGamesByUserId(Connection conn, long user_id)throws SQLException{
        String select = "SELECT COUNT(*) FROM games WHERE user_id = ?;";
        PreparedStatement pstm = conn.prepareStatement(select);
        pstm.setLong(1, user_id);
        ResultSet rs = pstm.executeQuery();

        rs.next();
        return rs.getInt(1);
    }

    public static long countAllMovesByUserId(Connection conn, long user_id)throws SQLException{
        String select = "SELECT SUM(moves) FROM games WHERE user_id = ?;";
        PreparedStatement pstm = conn.prepareStatement(select);
        pstm.setLong(1, user_id);
        ResultSet rs = pstm.executeQuery();

        rs.next();
        return rs.getLong(1);
    }

    public static long countAllTimeByUserId(Connection conn, long user_id)throws SQLException{
        String select = "SELECT SUM(time) FROM games WHERE user_id = ?;";
        PreparedStatement pstm = conn.prepareStatement(select);
        pstm.setLong(1, user_id);
        ResultSet rs = pstm.executeQuery();

        rs.next();
        return rs.getLong(1);
    }

    public static List<Game> load10BestMovesByUserIdOnLevel(Connection conn, long user_id, int level)throws SQLException{
        String select = "SELECT * FROM games WHERE user_id = ? AND level = ? ORDER BY moves ASC LIMIT 10;";
        PreparedStatement pstm = conn.prepareStatement(select);

        pstm.setLong(1, user_id);
        pstm.setInt(2, level);

        ResultSet rs = pstm.executeQuery();
        List<Game> games = set10Games(rs);

        return games;
    }

    public static List<Game> load10BestTimeByUserIdOnLevel(Connection conn, long user_id, int level)throws SQLException{
        String select = "SELECT * FROM games WHERE user_id = ? AND level = ? ORDER BY time ASC LIMIT 10;";
        PreparedStatement pstm = conn.prepareStatement(select);

        pstm.setLong(1, user_id);
        pstm.setInt(2, level);

        ResultSet rs = pstm.executeQuery();
        List<Game> games = set10Games(rs);

        return games;
    }

    private static List<Game> set10Games(ResultSet rs)throws SQLException{
        List<Game> games = new ArrayList<>();
        while(rs.next()){
            Game game = new Game();

            game.setId(rs.getLong(1));
            game.setUser_id(rs.getLong(2));
            game.setLevel(rs.getInt(3));
            game.setMoves(rs.getLong(4));
            game.setTime(rs.getLong(5));

            games.add(game);
        }
        return games;
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
