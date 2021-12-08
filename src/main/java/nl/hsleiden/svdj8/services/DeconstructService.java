package nl.hsleiden.svdj8.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class DeconstructService {

    public Map<String, Object> deconstructJson(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> jsonToMap = null;
        try {
            jsonToMap =  objectMapper.readValue(json, Map.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonToMap;
    }

}
