package org.apache.coyote.cookie;

import org.apache.catalina.session.Session;
import org.apache.catalina.session.SessionManager;
import org.apache.coyote.util.MapMaker;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class HttpCookie {

    private static final String JSESSIONID = "JSESSIONID";
    private static final String COOKIE_DELIMITER = ";";
    private static final String COOKIE_COMBINATOR = "=";
    private static final String END_SPACE = " ";

    private final Map<String, String> httpCookies;

    public HttpCookie(String cookies) {
        httpCookies = initCookie(cookies);
    }

    public static HttpCookie ofJSessionId(String sessionId) {
        return new HttpCookie(JSESSIONID + COOKIE_COMBINATOR + sessionId);
    }

    private Map<String, String> initCookie(String cookies) {
        List<String> httpCookies = List.of(cookies.split(COOKIE_DELIMITER));

        return httpCookies.stream()
                .map(cookie -> cookie.split(COOKIE_COMBINATOR))
                .collect(MapMaker.toMap(key -> key));
    }

    public Session getSession() {
        String sessionId = httpCookies.get(JSESSIONID);
        return SessionManager.getInstance().findSession(sessionId);
    }

    public String combineCookie() {
        return httpCookies.entrySet()
                .stream()
                .map(this::makeSingleCookie)
                .collect(Collectors.joining(COOKIE_DELIMITER));
    }

    private String makeSingleCookie(Entry<String, String> entry) {
        return entry.getKey() + COOKIE_COMBINATOR + entry.getValue() + END_SPACE;
    }
}
