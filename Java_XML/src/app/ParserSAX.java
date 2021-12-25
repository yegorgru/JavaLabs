/*
 * Yehor Hrushevyy
 *
 * Copyright (c) All Rights Reserved.
 */

package app;

import java.io.File;
import java.io.FileReader;

import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.sax.SAXSource;
import javax.xml.validation.Validator;

/**
 * class of Parser that implements Parser methods using sax parser
 *
 * @author  Yehor Hrushevyy
 * @version 1.0 2021-11-01
 */
public class ParserSAX extends Parser{
    private String source;

    /** parses new Guns from xml source using sax parser and adds to collection
     *
     * @param builder processes tags, attributes, etc
     * @throws Exception in case of invalid document or schema
     */
    @Override
    public void parse(GunBuilder builder) throws Exception {
        if(!isValid()) {
            throw new Exception("document is not valid");
        }
        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setSchema(schema);
        SAXParser saxParser = factory.newSAXParser();
        XMLReader reader = saxParser.getXMLReader();
        reader.setContentHandler(new GunHandler(builder));
        reader.parse(source);
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
        if(source == null) {
            return false;
        }
        try {
            Validator validator = schema.newValidator();
            validator.validate(new SAXSource(new InputSource(new FileReader(source))));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /** sets new source to parser
     *
     * @param source String path to source
     * @throws Exception in case of invalid path
     */
    @Override
    public void setSource(String source) throws Exception {
        File file = new File(source);
        if(!file.exists()) {
            throw new Exception("source is not valid");
        }
        this.source = source;
    }

    private static class GunHandler extends DefaultHandler {
        private GunBuilder builder;
        private String currentTag;

        public GunHandler(GunBuilder builder) {
            this.builder = builder;
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attrs) {
            if(builder.startElement(qName)) {
                builder.setCharacteristic(builder.getAttributeName(), attrs.getValue(0));
            }
            currentTag = qName;
        }

        @Override
        public void endElement(String uri, String localName, String qName) {
            builder.finishElement(qName);
        }

        @Override
        public void characters(char[] ch, int start, int length) {
            String value = new String(ch, start, length).trim();
            if(value.length() == 0) {
                return;
            }
            builder.setCharacteristic(currentTag, value);
        }
    }
}
