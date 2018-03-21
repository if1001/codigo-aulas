package br.ufpe.cin.if1001.threads;

public class Pessoa {
    private String nome;
    private String login;
    private String email;
    private String site;
    //...

    public Pessoa(String nome, String login) {
        this.nome = nome;
        this.login = login;
        this.email = login + "@cin.ufpe.br";
        this.site = "http://www.cin.ufpe.br/~"+login;
    }

    public String getNome() {
        return nome;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public String getSite() {
        return site;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setSite(String site) {
        this.site = site;
    }

    @Override
    public String toString() {
        return nome;
        //return nome + '(' + login + ')';
    }
}
