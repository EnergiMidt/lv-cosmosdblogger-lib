package no.em.util.log.cosmos.models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by ivar on 05/03/2018.
 */
public class PayloadLog implements Telemetry{
    public String message;
    public String payload;
    public String status;
    public String workflowId;
    public String workflowName;
    public String workflowStep;
    public String id;
    public String[] tags;



    public PayloadLog(){}

    public PayloadLog(String workflowName, String status){
        this.workflowName = workflowName;
        this.status = status;
    }
    public PayloadLog(String message, String payload, String status, String workflowId, String workflowName, String workflowStep) {
        this.message = message;
        this.payload = payload;
        this.status = status;
        this.workflowId = workflowId;
        this.workflowName = workflowName;
        this.workflowStep = workflowStep;
    }
    public PayloadLog(String message, String payload, String status, String workflowId, String workflowName, String workflowStep, String[] tags) {
        this.message = message;
        this.payload = payload;
        this.status = status;
        this.workflowId = workflowId;
        this.workflowName = workflowName;
        this.workflowStep = workflowStep;
        this.tags = tags;
    }

    public String serialize(ObjectMapper objectMapper) {
        String output = "";
        try {
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            Logger.getLogger(PayloadLog.class.getName()).log(Level.SEVERE, null, e);
        }
        return output;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getWorkflowId() {
        return workflowId;
    }

    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId;
    }

    public String getWorkflowName() {
        return workflowName;
    }

    public void setWorkflowName(String workflowName) {
        this.workflowName = workflowName;
    }

    public String getWorkflowStep() {
        return workflowStep;
    }

    public void setWorkflowStep(String workflowStep) {
        this.workflowStep = workflowStep;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }
}
