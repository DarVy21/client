package Entity;

public class UsersEntity{

    private int id;
    private String login;
    private String password;

    public UsersEntity(){}
    public UsersEntity(int idUser, String login, String password){
        this.id=idUser;
        this.password=password;
        this.login=login;
    }

    public int getId() {
        return id;
    }

    public void setIdUser(int idUser) {
        this.id = idUser;
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


}