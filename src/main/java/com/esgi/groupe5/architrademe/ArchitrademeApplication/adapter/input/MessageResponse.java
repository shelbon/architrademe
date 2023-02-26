package com.esgi.groupe5.architrademe.ArchitrademeApplication.adapter.input;

import java.util.LinkedHashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MessageResponse {
    private Map<String, Object> map = new LinkedHashMap<String, Object>();

    public MessageResponse(Integer status, String message) {
        this.map.put("status", status);
        this.map.put("message", message);
    }

    public MessageResponse(Integer status, Object data) {
        this.map.put("status", status);
        this.map.put("data", data);
    }
}
