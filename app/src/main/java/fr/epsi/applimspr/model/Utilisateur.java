package fr.epsi.applimspr;

class Utilisateur {
    private String token ;

    private String login ;


    private String password;

    public Utilisateur(String token){
        this.token = token;
    }
    public Utilisateur(String login,String password,String token){
        this.login = login;
        this.password = password;
        this.token = token;
    }
    public Utilisateur(String login,String password) {
        this.login = login;
        this.password = password;
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }


    public void setPassword(String password) {
        this.password = password;
    }

}
