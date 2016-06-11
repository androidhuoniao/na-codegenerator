package com.morcinek.android.codegenerator.plugin.ui;

import javax.swing.JLabel;

import com.intellij.openapi.project.Project;
import com.intellij.ui.components.JBTextField;

/**
 * Copyright 2014 Tomasz Morcinek. All rights reserved.
 */
public class CreateCodeDialogBuilder extends CodeDialogBuilder {

    private JBTextField creatorBeanText;

    public CreateCodeDialogBuilder(Project project, String title, String producedCode) {
        super(project, title, producedCode);
    }

    public void addBeanSection(String defaultText) {
        creatorBeanText = new JBTextField(defaultText);
        addUI(new JLabel(StringResources.CREATOR_BEAN_LABEL), creatorBeanText);
    }

    public String getCreatorBean() {
        return creatorBeanText.getText();
    }
}
