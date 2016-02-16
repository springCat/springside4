package org.springcat.sample.entity;

/**
 * Created by springcat on 16/2/15.
 */
public class UserExt extends User{

    private String plainPassword;

    public String getPlainPassword() {
        return plainPassword;
    }

    public void setPlainPassword(String plainPassword) {
        this.plainPassword = plainPassword;
    }
}
