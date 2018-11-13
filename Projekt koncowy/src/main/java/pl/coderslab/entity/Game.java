package pl.coderslab.entity;

import javax.persistence.*;

@Entity
@Table(name = "games")
public class Game{
    //Game long id, int level, long moves, long time, long user_id;
    //games: id BIGINT(20), level INT(1), moves BIGINT(20), time BIGINT(20), user_id BIGINT(20)

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int level;
    private long moves;
    private long time;

    public Game(){}

    public Game(int level, long moves, long time, User user){
        this.level = level;
        this.moves = moves;
        this.time = time;
        this.user = user;
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
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
