/*
 * Yehor Hrushevyy
 *
 * Copyright (c) All Rights Reserved.
 */

package app;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.stax.StAXSource;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.FileInputStream;

/**
 * class of Parser that implements Parser methods using stax parser
 *
 * @author  Yehor Hrushevyy
 * @version 1.0 2021-11-01
 */
public class ParserStAX extends Parser{
    private String source;
    private final XMLInputFactory inputFactory;
    private XMLStreamReader reader;

    /**
     * creates instance of ParserStAX
     */
    public ParserStAX() {
        inputFactory = XMLInputFactory.newInstance();
    }

    /** parses new Guns from xml source using stax parser and adds to collection
     *
     * @param builder processes tags, attributes, etc
     * @throws Exception in case of invalid document or schema
     */
    @Override
    public void parse(GunBuilder builder) throws Exception {
        if(!isValid()) {
            throw new Exception("document is not valid");
        }
        FileInputStream inputStream = new FileInputStream(source);
        reader = inputFactory.createXMLStreamReader(inputStream);
        while (reader.hasNext()) {
            String tag = "";
            switch (reader.next()) {
                case XMLStreamConstants.START_ELEMENT:
                    tag = reader.getLocalName();
                    if (builder.startElement(tag)) {
                        builder.setCharacteristic(builder.getAttributeName(),
                                reader.getAttributeValue(null, builder.getAttributeName())
                        );
                    }
                    else {
                        builder.setCharacteristic(tag, getString());
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    tag = reader.getLocalName();
                    builder.finishElement(tag);
                    break;
            }
        }
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
            validator.validate(new StAXSource(inputFactory.createXMLStreamReader(new FileInputStream(source))));
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

    private String getString() throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }
}
