package com.morcinek.android.codegenerator.plugin.utils;

import org.apache.velocity.util.StringUtils;

import com.morcinek.android.codegenerator.codegeneration.builders.file.ClassNameBuilder;
import com.morcinek.android.codegenerator.codegeneration.builders.file.PackageBuilder;
import com.morcinek.android.codegenerator.codegeneration.templates.TemplateManager;
import com.morcinek.android.codegenerator.extractor.string.FileNameExtractor;

/**
 * Copyright 2014 Tomasz Morcinek. All rights reserved.
 */
public class CreatorPathHelper {

    public String getMergedCodeWithPackage(String packageName, String generateCode) {
        if (!packageName.isEmpty()) {
            return new PackageBuilder(packageName).builtString() + generateCode;
        }
        return generateCode;
    }

    public String getFileName(String filePath, String resourceName) {
        String fileName = new FileNameExtractor().extractFromString(filePath);
        return new ClassNameBuilder(fileName).builtString() + resourceName + ".java";
    }

    public String getFolderPath(String sourcePath, String packageName) {
        String normalizePath = StringUtils.normalizePath(sourcePath + "/" + StringUtils.getPackageAsPath(packageName));
        return org.apache.commons.lang3.StringUtils.strip(normalizePath, "/");
    }

    public static String getMergedCode(String packageName, String beanName, String generateCode) {
        String code = "";
        if (!packageName.isEmpty()) {
            code = new PackageBuilder(packageName).builtString() + generateCode;
        }
        if (!beanName.isEmpty()) {
            TemplateManager templateManager = new TemplateManager(code);
            templateManager.addTemplateValue("BEAN", beanName);
            code = templateManager.getResult();
        }
        return code;
    }
}
