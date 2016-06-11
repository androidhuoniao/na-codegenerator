package com.morcinek.android.codegenerator.plugin.actions;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.vfs.VirtualFile;
import com.morcinek.android.codegenerator.codegeneration.providers.ResourceProvidersFactory;
import com.morcinek.android.codegenerator.codegeneration.providers.factories.CreatorResourceProvidersFactory;
import com.morcinek.android.codegenerator.plugin.codegenerator.CodeGeneratorController;
import com.morcinek.android.codegenerator.plugin.persistence.Settings;
import com.morcinek.android.codegenerator.plugin.ui.CodeDialogBuilder;
import com.morcinek.android.codegenerator.plugin.ui.CreateCodeDialogBuilder;
import com.morcinek.android.codegenerator.plugin.ui.StringResources;
import com.morcinek.android.codegenerator.plugin.utils.CreatorPathHelper;

/**
 * Copyright 2014 Tomasz Morcinek. All rights reserved.
 */
public class CreatorAction extends LayoutAction {

    @Override
    protected String getResourceName() {
        return "";
    }

    @Override
    protected String getTemplateName() {
        return "Creator_template";
    }

    @Override
    protected ResourceProvidersFactory getResourceProvidersFactory() {
        return new CreatorResourceProvidersFactory();
    }

    @Override
    protected void showCodeDialog(AnActionEvent event, final Project project, final VirtualFile selectedFile,
                                  Settings settings)
            throws ParserConfigurationException, SAXException, XPathExpressionException, IOException {

        CodeGeneratorController codeGeneratorController = new CodeGeneratorController(getTemplateName(),
                getResourceProvidersFactory());
        String generatedCode = codeGeneratorController.generateCode(project, selectedFile, event.getData(
                PlatformDataKeys.EDITOR));

        final CreateCodeDialogBuilder codeDialogBuilder = new CreateCodeDialogBuilder(project,
                String.format(StringResources.TITLE_FORMAT_TEXT, selectedFile.getName()), generatedCode);
        codeDialogBuilder.addSourcePathSection(projectHelper.getSourceRootPathList(project, event),
                settings.getSourcePath());
        codeDialogBuilder.addPackageSection(packageHelper.getPackageName(project, event));
        codeDialogBuilder.addBeanSection("");

        codeDialogBuilder.addAction(StringResources.CREATE_ACTION_LABEL, new Runnable() {
            @Override
            public void run() {
                try {
                    createFileWithGeneratedCode(codeDialogBuilder, selectedFile, project);
                } catch (IOException exception) {
                    errorHandler.handleError(project, exception);
                }
            }
        }, true);
        if (codeDialogBuilder.showDialog() == DialogWrapper.OK_EXIT_CODE) {
            settings.setSourcePath(codeDialogBuilder.getSourcePath());
        }
    }

    @Override
    protected String getFinalCode(CodeDialogBuilder codeDialogBuilder) {
        CreateCodeDialogBuilder builder = (CreateCodeDialogBuilder) codeDialogBuilder;
        String packageName = builder.getPackage();
        String beanName = builder.getCreatorBean();
        String modifiedCode = builder.getModifiedCode();
        return CreatorPathHelper.getMergedCode(packageName, beanName, modifiedCode);
    }
}
