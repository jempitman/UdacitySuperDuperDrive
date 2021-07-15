package com.udacity.jwdnd.course1.cloudstorage.model;

public class HomeForm {

    private Boolean logout;
    private Boolean logoutError;

    public HomeForm(Boolean logout, Boolean logoutError) {
        this.logout = logout;
        this.logoutError = logoutError;
    }

    public Boolean getLogout() {
        return logout;
    }

    public void setLogout(Boolean logout) {
        this.logout = logout;
    }

    public Boolean getLogoutError() {
        return logoutError;
    }

    public void setLogoutError(Boolean logoutError) {
        this.logoutError = logoutError;
    }
}
