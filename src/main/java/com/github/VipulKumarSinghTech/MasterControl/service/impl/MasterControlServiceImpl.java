package com.github.VipulKumarSinghTech.MasterControl.service.impl;

import com.github.VipulKumarSinghTech.MasterControl.service.MasterControlService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@Service
public class MasterControlServiceImpl implements MasterControlService {

    @PersistenceContext(unitName = "entityManagerFactory")
    private EntityManager entityManager;

    public static Map<String, String> INDEX;

    @Override
    public Map<String, String> getIndex() {
        return INDEX;
    }

    @Override
    public Object findById(String key, Object id) throws ClassNotFoundException {
        Class<?> clazz = getClassByKey(key);
        return entityManager.find(clazz, id);
    }

    @Override
    public List findAll(String key) throws ClassNotFoundException {
        String className = getIndex().get(key);
        Class<?> clazz = Class.forName(className);
        return entityManager.createQuery("from " + clazz.getName()).getResultList();
    }

    @Override
    @Transactional
    public Object createData(String key, Map<String, Object> fieldValueMap) throws ReflectiveOperationException {
        Class<?> clazz = getClassByKey(key);
        Constructor<?> cons = clazz.getConstructor();
        Object object = cons.newInstance();
        fillDataFromMap(fieldValueMap, clazz, object);
        return entityManager.merge(object);
    }

    @Override
    @Transactional
    public Object updateData(String key, Object id, Map<String, Object> fieldValueMap) throws ReflectiveOperationException {
        Class<?> clazz = getClassByKey(key);
        Object object = findById(key, id);
        fillDataFromMap(fieldValueMap, clazz, object);
        return entityManager.merge(object);
    }

    @Override
    @Transactional
    public void deleteById(String key, Object id) throws ReflectiveOperationException {
        Object object = findById(key, id);
        entityManager.remove(object);
    }

    private Class<?> getClassByKey(String key) throws ClassNotFoundException {
        String className = getIndex().get(key);
        return Class.forName(className);
    }

    private void fillDataFromMap(Map<String, Object> fieldValueMap, Class<?> clazz, Object object) throws NoSuchFieldException, IllegalAccessException {
        for (Map.Entry<String, Object> entry : fieldValueMap.entrySet()) {
            String fieldName = entry.getKey();
            Object value = entry.getValue();

            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(object, value);
        }
    }
}
