package com.github.VipulKumarSinghTech.MasterControl.service.impl;

import com.github.VipulKumarSinghTech.MasterControl.annotation.MasterControl;
import com.github.VipulKumarSinghTech.MasterControl.exceptions.MasterControlException;
import com.github.VipulKumarSinghTech.MasterControl.service.MasterControlService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.ScannedGenericBeanDefinition;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class MasterControlServiceImpl implements MasterControlService {

    /**
     * basePackage = blank - searches for all packages, and thus is relatively slow
     * It is recommended to use your model directory to make this scanning faster
     */
    @Value("${admin.base.package:}")
    private String basePackage;

    @PersistenceContext(unitName = "entityManagerFactory")
    private EntityManager entityManager;

    private static Map<String, String> INDEX;

    @Override
    public Map<String, String> getIndex() {
        if (INDEX == null)
            initializeIndex();
        return INDEX;
    }

    @Override
    public Object findById(String key, Object id) throws ClassNotFoundException {
        String className = getIndex().get(key);
        Class<?> clazz = Class.forName(className);
        return entityManager.find(clazz, id);
    }

    @Override
    public List findAll(String key) throws ClassNotFoundException {
        String className = getIndex().get(key);
        Class<?> clazz = Class.forName(className);
        return entityManager.createQuery("from " + clazz.getName()).getResultList();
    }

    private void initializeIndex() {
        ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
        provider.addIncludeFilter(new AnnotationTypeFilter(MasterControl.class));
        final Set<BeanDefinition> candidates = provider.findCandidateComponents(basePackage);

        Map<String, String> indexMap = new HashMap<>();
        candidates.forEach(candidate -> {
            String className = candidate.getBeanClassName();
            String key = getNameProperty(candidate, className);
            if (indexMap.containsKey(key)) {
                throw new MasterControlException("Duplicate name found in " + className);
            } else {
                indexMap.put(key, className);
            }
        });

        INDEX = indexMap;
    }

    private String getNameProperty(BeanDefinition candidate, String className) {
        String name = ((ScannedGenericBeanDefinition) candidate)
                .getMetadata()
                .getAnnotationAttributes("com.github.VipulKumarSinghTech.MasterControl.annotation.MasterControl")
                .get("name").toString();

        if (name.trim().isEmpty())
            name = className.substring(className.lastIndexOf('.') + 1);

        return name;
    }
}
