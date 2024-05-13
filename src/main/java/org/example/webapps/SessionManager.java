package org.example.webapps;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public class SessionManager {
    private static final Map<String, HttpSession> sessionMap = new HashMap<>();

    // Retrieve HttpSession object by session ID
    public static HttpSession getSession(String sessionId) {
        return sessionMap.get(sessionId);
    }

    // Add HttpSession object with session ID to map
    public static void addSession(String sessionId, HttpSession session) {
        sessionMap.put(sessionId, session);
    }

    // Remove HttpSession object from map based on session ID
    public static void removeSession(String sessionId) {
        sessionMap.remove(sessionId);
    }
}

