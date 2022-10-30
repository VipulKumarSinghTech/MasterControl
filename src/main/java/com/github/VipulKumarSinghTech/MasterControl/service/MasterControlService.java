package com.github.VipulKumarSinghTech.MasterControl.service;

import java.util.List;
import java.util.Map;

public interface MasterControlService {

    /**
     * @return
     */
    Map<String, String> getIndex();

    /**
     * @param key
     * @param id
     * @return
     * @throws ClassNotFoundException
     */
    Object findById(String key, Object id) throws ClassNotFoundException;

    /**
     * @param key
     * @return
     * @throws ClassNotFoundException
     */
    List<Object> findAll(String key) throws ClassNotFoundException;

}
