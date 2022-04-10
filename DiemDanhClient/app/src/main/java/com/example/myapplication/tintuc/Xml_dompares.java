package com.example.myapplication.tintuc;

import android.util.Log;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.StringReader;
import java.lang.annotation.Documented;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class Xml_dompares {
    public Documented getDocumented(String xml){
        Documented documented = null;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = factory.newDocumentBuilder();
            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(xml));
            is.setEncoding("UTF-8");
            documented = (Documented) db.parse(is);
        }catch (ParserConfigurationException e){
            Log.e("Error", e.getMessage(), e);
            return null;
        }
        catch (SAXException e){
            Log.e("Error",e.getMessage(), e);
            return null;
        }
        catch (IOException e){
            Log.e("Error",e.getMessage(), e);
        }
        return documented;
    }
    public String getValue(Element item, String name){
        NodeList nodes = item.getElementsByTagName(name);
        return this.getTextNodeValue(nodes.item(0));
    }
    private final String getTextNodeValue(Node elem){
        Node child;
        if (elem != null){
            if (elem.hasChildNodes()){
                for (child = elem.getFirstChild(); child != null; child = child.getNextSibling())
                    if (child.getNodeType() == Node.TEXT_NODE){
                        return child.getNodeValue();
                    }
            }
        }
        return "";
    }
}
