package com.github.VipulKumarSinghTech.MasterControl.service;

import java.util.Map;

public interface MasterControlService {

    Map<String, String> getIndex();

    Object findById(String key, Object id) throws ClassNotFoundException;

}
