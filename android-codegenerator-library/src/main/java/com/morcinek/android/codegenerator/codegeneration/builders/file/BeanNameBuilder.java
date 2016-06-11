package com.morcinek.android.codegenerator.codegeneration.builders.file;

import com.morcinek.android.codegenerator.codegeneration.builders.CodeBuilder;

/**
 * @author grass
 */
public class BeanNameBuilder implements CodeBuilder {

    private String beanName;

    public BeanNameBuilder(String beanName) {
        this.beanName = beanName;
    }

    @Override
    public String builtString() {
        return getResourceName(beanName);
    }

    private String getResourceName(String beanName) {
        return beanName;
    }

    @Override
    public String getKey() {
        return "BEAN";
    }
}
