package spring.beans;

import other.BCrypt;

public class User {
    private int id;
    private String password;
    private String email;

    public User(String password, String email) {
        setPassword(password);
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }
    public User setPassword(String password) {
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
        return this;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
