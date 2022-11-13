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

}
