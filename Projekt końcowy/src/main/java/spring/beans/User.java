package spring.beans;

import other.BCrypt;

public class User {
    private int id;
    private String password;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }
    public User setPassword(String password) {
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
        return this;
    }
}
