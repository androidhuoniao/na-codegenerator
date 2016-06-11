package com.morcinek.android.codegenerator;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import com.morcinek.android.codegenerator.codegeneration.TemplateCodeGenerator;
import com.morcinek.android.codegenerator.codegeneration.providers.factories.CreatorResourceProvidersFactory;
import com.morcinek.android.codegenerator.codegeneration.templates.ResourceTemplatesProvider;
import com.morcinek.android.codegenerator.codegeneration.templates.TemplatesProvider;
import com.morcinek.android.codegenerator.extractor.XMLResourceExtractor;
import com.morcinek.android.codegenerator.extractor.string.FileNameExtractor;
import com.morcinek.android.codegenerator.util.InputStreamProvider;

/**
 * Created by grass on 16/6/10.
 */
public class CreatorCodeGeneratorTest {

    private CodeGenerator codeGenerator;

    private InputStreamProvider inputStreamProvider = new InputStreamProvider();

    private TemplatesProvider templatesProvider = new ResourceTemplatesProvider();

    @Before
    public void setUp() throws Exception {
        codeGenerator = new CodeGenerator(XMLResourceExtractor.createResourceExtractor(), new FileNameExtractor(),
                new TemplateCodeGenerator("Creator_template", new CreatorResourceProvidersFactory(),
                        new ResourceTemplatesProvider()));
    }

    @Test
    public void viewPagerProduceCodeTest() throws Exception {
        String producedCode = produceCodeFromFilePath("codegeneration/layouts/circle_qb_question_creator.xml");
        System.out.println(producedCode);
    }

    private String produceCodeFromFilePath(String filePath) throws ParserConfigurationException, SAXException,
            XPathExpressionException, IOException {
        return codeGenerator.produceCode(inputStreamProvider.getStreamFromResource(filePath), filePath);
    }
}
