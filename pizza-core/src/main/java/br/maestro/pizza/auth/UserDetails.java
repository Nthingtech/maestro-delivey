package br.maestro.pizza.auth;


public class UserDetails {
    public boolean isAnonymous = true;
    public String username;

    public UserDetails() {
    }

    public UserDetails(boolean isAnonymous, String username) {
        this.isAnonymous = isAnonymous;
        this.username = username;
    }
}
