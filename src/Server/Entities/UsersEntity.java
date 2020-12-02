package Server.Entities;

import java.io.Serializable;
import java.util.Objects;

public class UsersEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id_user;
    private String login;
    private String password;

    public UsersEntity(){}
    public UsersEntity( String login, String password){
        this.password=password;
        this.login=login;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String username) {
        this.login = username;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UsersEntity)) return false;
        UsersEntity that = (UsersEntity) o;
        return id_user == that.id_user &&
                getLogin().equals(that.getLogin()) &&
                getPassword().equals(that.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_user, getLogin(), getPassword());
    }
}