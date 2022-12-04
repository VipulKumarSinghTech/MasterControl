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
     * @throws ClassNotFoundException
     */
    Object findById(String key, Object id) throws ClassNotFoundException;

    /**
     * @param key entity key
     * @return list of all objects in specified entity
     * @throws ClassNotFoundException
     */
    List<Object> findAll(String key) throws ClassNotFoundException;

    /**
     * @param key
     * @param fieldValueMap
     * @return created object
     * @throws ReflectiveOperationException
     */
    Object createData(String key, Map<String, Object> fieldValueMap) throws ReflectiveOperationException;

    /**
     * @param key
     * @param id
     * @param fieldValueMap
     * @return upated object
     * @throws ReflectiveOperationException
     */
    Object updateData(String key, Object id, Map<String, Object> fieldValueMap) throws ReflectiveOperationException;

    /**
     * @param key
     * @param id
     * @throws ReflectiveOperationException
     */
    void deleteById(String key, Object id) throws ReflectiveOperationException;

}
