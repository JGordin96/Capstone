package com.techelevator.security;

import java.util.Arrays;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.techelevator.model.User;
import com.techelevator.model.UserDAO;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionAuthProvider implements AuthProvider {

	public static final String USER_KEY = "appCurrentUser";

    private HttpSession session;
    private UserDAO dao;

    @Autowired
    public SessionAuthProvider(HttpSession session, UserDAO dao) {
        this.session = session;
        this.dao = dao;
    }

    @Override
    public boolean isLoggedIn() {
        return session.getAttribute(USER_KEY) != null;
    }

    @Override
    public User getCurrentUser() {
        return (User) session.getAttribute(USER_KEY);
    }

    @Override
    public boolean signIn(String userName, String password) {
        User authenticatedUser = dao.getUserByUserName(userName);
        if (authenticatedUser != null) {
            session.setAttribute(USER_KEY, authenticatedUser);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void logOff() {
        session.removeAttribute(USER_KEY);
        session.invalidate();
    }

    @Override
    public void register(String username, String password, String confirmPassword, String address, int milerange) {
        dao.saveUser(username, password, confirmPassword, address, milerange);
    }

    @Override
    public boolean userHasRole(String[] roles) {
        User currentUser = getCurrentUser();
        if (currentUser != null && roles != null) {
            return Arrays.asList(roles).contains(currentUser.getRole());
        } else {
            return false;
        }
    }
}
