package com.epam.task1.sax;

import com.epam.task1.exception.XMLParseException;
import com.epam.model.Speech;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by Olga Kramska on 12-Apr-16.
 */
public class SAXPlayParser {
    private static final String LOAD_EXTERNAL_DTD = "http://apache.org/xml/features/nonvalidating/load-external-dtd";

    private SAXPlayParser() {
    }

    public static List<Speech> getSpeechesFromXML(InputStream inputStream) {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser;
        try {
            factory.setFeature(LOAD_EXTERNAL_DTD, false);
            parser = factory.newSAXParser();
        } catch (ParserConfigurationException | SAXException e) {
            throw new XMLParseException(e.getMessage(), e);
        }

        SAXPlayHandler handler = new SAXPlayHandler();
        try {
            parser.parse(new InputSource(inputStream), handler);
        } catch (SAXException | IOException e) {
            throw new XMLParseException(e.getMessage(), e);
        }
        return handler.getSpeechList();
    }
}
