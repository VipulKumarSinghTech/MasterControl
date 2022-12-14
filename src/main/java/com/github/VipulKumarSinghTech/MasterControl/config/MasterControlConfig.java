package com.github.VipulKumarSinghTech.MasterControl.config;

import com.github.VipulKumarSinghTech.MasterControl.annotation.MasterControl;
import com.github.VipulKumarSinghTech.MasterControl.exceptions.MasterControlException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ScannedGenericBeanDefinition;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static com.github.VipulKumarSinghTech.MasterControl.service.impl.MasterControlServiceImpl.INDEX;

@Configuration
public class MasterControlConfig {

    /**
     * basePackage = blank - searches for all packages, and thus is relatively slow
     * It is recommended to use your model directory to make this scanning faster
     */
    @Value("${admin.base.package:}")
    private String basePackage;

    @PostConstruct
    public void index() {
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
