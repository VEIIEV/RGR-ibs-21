package com.example.auction.DTO;

import lombok.Data;

@Data
public class UserDTO {
    private int id;
    private String email;
    private String login;
    private String password;
    private boolean is_adm;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isIs_adm() {
        return is_adm;
    }

    public void setIs_adm(boolean is_adm) {
        this.is_adm = is_adm;
    }
}
