/*
 * Yehor Hrushevyy
 *
 * Copyright (c) All Rights Reserved.
 */

package app;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.validation.Validator;
import java.io.File;

/**
 * class of Parser that implements Parser methods using dom parser
 *
 * @author  Yehor Hrushevyy
 * @version 1.0 2021-11-01
 */
public class ParserDOM extends Parser {
    private Document document;
    private GunBuilder builder;

    /** parses new Guns from xml source using dom parser and adds to collection
     *
     * @param builder processes tags, attributes, etc
     * @throws Exception in case of invalid document or schema
     */
    @Override
    public void parse(GunBuilder builder) throws Exception {
        this.builder = builder;
        if(!isValid()) {
            throw new Exception("document is not valid");
        }
        Element root = document.getDocumentElement();
        NodeList nodes = root.getElementsByTagName(builder.getElementName());
        for (int i = 0; i < nodes.getLength(); i++) {
            buildElement((Element) nodes.item(i));
        }
    }

    /** sets new source to parser
     *
     * @param source String path to source
     * @throws Exception in case of invalid path
     */
    @Override
    public void setSource(String source) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        document = builder.parse(new File(source));
    }

    /** checks if document and schema are valid
     *
     * @return boolean if document and schema are not null and document fits schema
     */
    @Override
    public boolean isValid() {
        if(schema == null) {
            return false;
        }
        if(document == null) {
            return false;
        }
        try {
            Validator validator = schema.newValidator();
            validator.validate(new DOMSource(document));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void buildElement(Element element) {
        builder.startElement(builder.getElementName());
        for(String characteristic : builder.getCharacteristicNames()) {
            builder.setCharacteristic(characteristic, getString(element, characteristic));
        }
        builder.finishElement(builder.getElementName());
    }

    private String getString(Element element, String elementName) {
        if(elementName == builder.getAttributeName()) {
            return element.getAttribute(elementName);
        }
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        return node.getTextContent();
    }
}
