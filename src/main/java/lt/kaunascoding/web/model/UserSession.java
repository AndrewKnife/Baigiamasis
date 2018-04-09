package lt.kaunascoding.web.model;

import lt.kaunascoding.web.model.mysql.classes.User;

public class UserSession {
    private static User user;

    public UserSession(User user) {
        this.user = user;
    }

    public static User getUser() {
        return user;
    }
}
