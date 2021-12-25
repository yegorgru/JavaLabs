/*
 * Yehor Hrushevyy
 *
 * Copyright (c) All Rights Reserved.
 */

package app;

import javax.xml.XMLConstants;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.util.ArrayList;

/**
 * abstract class of Parser that implements setSchema method
 * and declare parseGuns, isValid, setSource methods
 *
 * @author  Yehor Hrushevyy
 * @version 1.0 2021-11-01
 */
public abstract class Parser {
    protected Schema schema;

    /** sets new schema to parser
     *
     * @param path String path to schema
     * @throws Exception in case of invalid path
     */
    public void setSchema(String path) throws Exception {
        String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        SchemaFactory factory = SchemaFactory.newInstance(language);
        schema = factory.newSchema(new File(path));
    }

    /** parses new Guns from xml source and adds to collection
     *
     * @param guns collection to add new Gun objects
     * @throws Exception in case of invalid document or schema
     */
    public abstract void parse(GunBuilder builder) throws Exception;

    /** checks if document and schema are valid
     *
     * @return boolean if document and schema are not null and document fits schema
     */
    public abstract boolean isValid();

    /** sets new source to parser
     *
     * @param source String path to source
     * @throws Exception in case of invalid path
     */
    public abstract void setSource(String source) throws Exception;
}
