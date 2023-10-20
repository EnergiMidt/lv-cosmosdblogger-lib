package no.em.util.log.cosmos.models;

/**
 * Created by ivar on 06/09/2018.
 */
public class SimpleResponse {
    public int statusCode;
    public String body;

    public SimpleResponse(int statusCode) {
        this.statusCode = statusCode;
    }

    public SimpleResponse(int statusCode, String body) {
        this.statusCode = statusCode;
        this.body = body;
    }
}
