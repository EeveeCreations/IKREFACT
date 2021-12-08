package nl.hsleiden.svdj8.models;

import java.util.Map;

public class Api {

    private Map<String, Object> oldData;
    private Map<String, Object> newData;

    public Api(Map<String, Object> oldData, Map<String, Object> newData) {
        this.oldData = oldData;
        this.newData = newData;
    }

    public Map<String, Object> getOldData() {
        return oldData;
    }

    public void setOldData(Map<String, Object> oldData) {
        this.oldData = oldData;
    }

    public Map<String, Object> getNewData() {
        return newData;
    }

    public void setNewData(Map<String, Object> newData) {
        this.newData = newData;
    }
}
