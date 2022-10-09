package com.github.VipulKumarSinghTech.MasterControl.service.impl;

import com.github.VipulKumarSinghTech.MasterControl.annotation.MasterControl;
import com.github.VipulKumarSinghTech.MasterControl.service.MasterControlService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.ScannedGenericBeanDefinition;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MasterControlServiceImpl implements MasterControlService {

    /**
     * basePackage = blank - searches for all packages, and thus is relatively slow
     * It is recommended to use your model directory to make this scanning faster
     */
    @Value("${admin.base.package:}")
    private String basePackage;

    private static List<String> index;

    @Override
    public List<String> getIndex() {
        if (index == null) {
            initializeIndex();
        }
        return index;
    }

    private void initializeIndex() {
        ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
        provider.addIncludeFilter(new AnnotationTypeFilter(MasterControl.class));

        final Set<BeanDefinition> candidates = provider.findCandidateComponents(basePackage);
        index = candidates.stream().map(BeanDefinition::getBeanClassName).collect(Collectors.toList());

        candidates.forEach(candidate ->{
            System.out.println(((ScannedGenericBeanDefinition) candidate)
                    .getMetadata()
                    .getAnnotationAttributes("com.github.VipulKumarSinghTech.MasterControl.annotation.MasterControl")
                    .get("name"));
        });
    }
}
