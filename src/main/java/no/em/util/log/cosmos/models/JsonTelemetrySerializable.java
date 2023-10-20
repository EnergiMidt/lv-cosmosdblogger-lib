package no.em.util.log.cosmos.models;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by ivar on 28/06/2018.
 */
public interface JsonTelemetrySerializable {

    String serialize(ObjectMapper objectMapper);
}
