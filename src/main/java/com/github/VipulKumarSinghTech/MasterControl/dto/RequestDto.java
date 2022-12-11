package com.github.VipulKumarSinghTech.MasterControl.dto;

import java.util.Map;

public class RequestDto {

    private Object id;
    private String key;
    private Map<String, Object> fieldValueMap;

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Map<String, Object> getFieldValueMap() {
        return fieldValueMap;
    }

    public void setFieldValueMap(Map<String, Object> fieldValueMap) {
        this.fieldValueMap = fieldValueMap;
    }
}
