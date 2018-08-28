package com.example.RESTfulServices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;


@ControllerAdvice
public class Utils {

    public Logger logger = LoggerFactory.getLogger(Utils.class);

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity httpClientException(final HttpClientErrorException e) {
        switch (e.getLocalizedMessage().trim()){
            case "404":
                return new ResponseEntity("404 - NOT FOUND!", HttpStatus.NOT_FOUND);

            case "500":
                return new ResponseEntity("500 - INTERNAL SERVER ERROR!", HttpStatus.INTERNAL_SERVER_ERROR);

            default:
                return new ResponseEntity("Other problem occurred!", HttpStatus.SEE_OTHER);
        }
    }
}
