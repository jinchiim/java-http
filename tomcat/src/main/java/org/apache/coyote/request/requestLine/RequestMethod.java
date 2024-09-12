package org.apache.coyote.request.requestLine;

import java.util.Arrays;

public enum RequestMethod {

    GET,
    POST,
    PUT,
    PATCH,
    DELETE;

    public static RequestMethod findMethod(String method) {
        return Arrays.stream(RequestMethod.values())
                .filter(requestMethod -> requestMethod.name().equals(method.trim()))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않은 Method 입니다."));
    }
}
