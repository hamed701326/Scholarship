package ir.maktab.bours.core.share;


import ir.maktab.bours.model.User;

public class AuthenticationService {

    private User loginUser;

    private static AuthenticationService authenticationService = null;
    public static AuthenticationService getInstance() {
        if (authenticationService == null) {
            authenticationService = new AuthenticationService();
        }
        return authenticationService;
    }

    private AuthenticationService() {

    }

    public void setLoginUser(User user) {
        this.loginUser = user;
    }

    public User getLoginUser() {
        return loginUser;
    }
}
