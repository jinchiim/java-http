package com.techcourse.controller;

import org.apache.catalina.controller.AbstractController;
import org.apache.coyote.request.HttpRequest;
import org.apache.coyote.response.HttpResponse;
import org.apache.coyote.util.ContentType;
import org.apache.coyote.util.HttpStatus;

public class DefaultController extends AbstractController {

    private static final String DEFAULT_BODY = "Hello world!";

    @Override
    public boolean canHandle(HttpRequest httpRequest) {
        return httpRequest.isDefaultRequestPath();
    }

    @Override
    public void doPost(HttpRequest httpRequest, HttpResponse httpResponse) {
        httpResponse.sendError(HttpStatus.NOT_FOUND);
    }

    @Override
    public void doGet(HttpRequest httpRequest, HttpResponse httpResponse) {
        httpResponse.sendBodyResponse(DEFAULT_BODY, ContentType.PLAIN);
    }
}
