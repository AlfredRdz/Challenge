package com.darkzy.disney.dto;



public class LoginRespuesta {

    private String jwt;

    public LoginRespuesta(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
