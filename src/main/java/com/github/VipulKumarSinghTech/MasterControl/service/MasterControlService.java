package com.github.VipulKumarSinghTech.MasterControl.service;

import java.util.List;
import java.util.Map;

public interface MasterControlService {

    /**
     * @return index list
     */
    Map<String, String> getIndex();

    /**
     * @param key entity key
     * @param id  primary key
     * @return queried object based on primary key
     * @throws ClassNotFoundException throws exception if class does not exist
     */
    Object findById(String key, Object id) throws ClassNotFoundException;

    /**
     * @param key entity key
     * @return list of all objects in specified entity
     * @throws ClassNotFoundException throws exception if class does not exist
     */
    List<Object> findAll(String key) throws ClassNotFoundException;

    /**
     * @param key           entity key
     * @param fieldValueMap field value map
     * @return created object
     * @throws ReflectiveOperationException throws exception if reflection operation failed
     */
    Object createData(String key, Map<String, Object> fieldValueMap) throws ReflectiveOperationException;

    /**
     * @param key           entity key
     * @param id            primary key for this entity
     * @param fieldValueMap field value map
     * @return upated object
     * @throws ReflectiveOperationException throws exception if reflection operation failed
     */
    Object updateData(String key, Object id, Map<String, Object> fieldValueMap) throws ReflectiveOperationException;

    /**
     * @param key entity key
     * @param id  primary key for this entity
     * @throws ReflectiveOperationException throws exception if reflection operation failed
     */
    void deleteById(String key, Object id) throws ReflectiveOperationException;

}
