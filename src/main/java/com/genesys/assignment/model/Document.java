package com.genesys.assignment.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import org.json.JSONArray;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Document implements Serializable {

    @Id
    private UUID uuid;

    public String name;
    public List<Map<String, String>> csvPayload;

    public Document(){}

    public Document(String name, List<Map<String, String>> csvPayload){
        this.uuid =  UUID.randomUUID();
        this.name = name;
        this.csvPayload = csvPayload;
    }
    public String getName(){
        return this.name;
    }

    public List<Map<String, String>> getCsvPayload(){
        return this.csvPayload;
    }
    public UUID uuid(){
        return this.uuid;
    }


    public void setCsvPayload(List<Map<String, String>> csvPayload){
        this.csvPayload = csvPayload;
    }
    public void setUuid(UUID uuid){
        this.uuid = uuid;
    }
    public void setName(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return "{" +
                ", uuid=" + uuid +
                ", csvPayload=" + csvPayload +
                '}';
    }
}
