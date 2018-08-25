package com.example.RESTfulServices.controllers;


import okhttp3.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.util.Arrays;
import static org.springframework.web.bind.annotation.RequestMethod.*;


@RestController
@RequestMapping("/airports")
public class AirportController {

    public void controllerEnterPoint(){
        System.out.println("\n-------- AirportController -------- ");
    }

    OkHttpClient clientHTTP1 = new OkHttpClient.Builder()
            .protocols(Arrays.asList(Protocol.HTTP_1_1))
            .build();

    /**
     *
     * H2_PRIOR_KNOWLEDGE -> h2c
     * The prior_knowledge => no upgrade negotiation needed
     *                     => using this means we are sure that the other client is using HTTP/2
     *
     * */
    OkHttpClient clientHTTP2 = new OkHttpClient.Builder()
            .protocols(Arrays.asList(Protocol.H2_PRIOR_KNOWLEDGE)) //it's h2c
            .build();


    @RequestMapping(method = GET, produces = "application/json")
    public String getAirports() throws IOException {
        controllerEnterPoint();
        System.out.println("\n ** You are in /airports endpoint ** ");

        Request request = new Request.Builder()
                .url("http://localhost:9001/flights/api/v1/airports")
                .get()
                .build();

        Response response = clientHTTP2.newCall(request).execute();

        System.out.println("Response headers: " + response.headers());


        /**
         *
         * You can call string() once because response body can be huge so OkHttp doesn’t store it in memory, it reads it as a stream from network when you need it.
         *
         * When you read body as a string() OkHttp downloads response body and returns it to you without keeping reference to the string, it can’t be downloaded twice
         * without new request.
         *
         *
         * So NO System.out.println("Response body: " + response.body().toString());  (or .string())
         *
         * */
        return response.body().string();
    }


    /*@RequestMapping(value = "/{id}", method = GET)
    public ResponseEntity getSomething(@PathVariable Long id) {
        Something something = somethingService.getSomething(id);
        if (something == null) {
            return new ResponseEntity("No \"Something\" found to display for ID " + id, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(something, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = DELETE)
    public ResponseEntity deleteSomething(@PathVariable Long id) {
        if (somethingService.deleteSomething(id) == null) {
            return new ResponseEntity("No \"Something\" found to delete for ID " + id, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(id, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = PUT)
    public ResponseEntity updateSomething(@PathVariable Long id, @RequestBody Something something) {
        something = somethingService.updateSomething(id, something);
        if (something == null) {
            return new ResponseEntity("No \"Something\" found to update for ID " + id, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(something, HttpStatus.OK);
    }*/


    @RequestMapping(value = "/index", method = GET)
    public String index() {
        return "Congratulations from BlogController.java";
    }
}
