package org.example.webapps;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.servlet.http.HttpSession;

@Stateful
@LocalBean
public class SessionBean {

    private HttpSession httpSession;
    private String username;

    public void createSession(String sessionId, String username) {
        // Get the HttpSession object associated with the provided session ID
        httpSession = SessionManager.getSession(sessionId);
        // Store the username in the session
        this.username = username;
    }

    public String getUsername(String sessionId) {
        if (httpSession != null && httpSession.getId().equals(sessionId)) {
            // Ensure that the provided session ID matches the session's ID
            return username;
        } else {
            return null; // Session ID doesn't match or session is invalid
        }
    }

    public void invalidateSession() {
        if (httpSession != null) {
            // Invalidate the session
            httpSession.invalidate();
            httpSession = null;
            // Clear username
            username = null;
        }
    }
}
