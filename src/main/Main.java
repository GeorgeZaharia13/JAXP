/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/**
 * @author George
 */
public class Main {

    /**
     * @param args the command line arguments
     * @throws javax.xml.xpath.XPathExpressionException
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws XPathExpressionException, FileNotFoundException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        documentBuilderFactory.setIgnoringElementContentWhitespace(true);

        XPathFactory xPathFactory = XPathFactory.newInstance();
        XPath xPath = xPathFactory.newXPath();
        XPathExpression xPE = xPath.compile("//book[price>10 and translate(publish_date,'-','')>20050000]");

        File xmlDocument = new File("resources/books.xml");
        InputSource inputSource = new InputSource(new FileInputStream(xmlDocument));

        Object result = xPE.evaluate(inputSource, XPathConstants.NODESET);
        NodeList nodeList = (NodeList) result;

        for (int i = 0; i < nodeList.getLength(); i++) {
            System.out.print(nodeList.item(i).getNodeName() + " ");
            System.out.println(nodeList.item(i).getAttributes().item(0));
            System.out.println(" Author: " + nodeList.item(i).getFirstChild().getNextSibling().getTextContent());
            System.out.println(" Title: " + nodeList.item(i).getFirstChild().getNextSibling().
                    getNextSibling().getNextSibling().getTextContent());
            System.out.println(" Genre: " + nodeList.item(i).getFirstChild().getNextSibling().
                    getNextSibling().getNextSibling().getNextSibling().getNextSibling().getTextContent());
            System.out.println(" Price: " + nodeList.item(i).getLastChild().getPreviousSibling().
                    getPreviousSibling().getPreviousSibling().getPreviousSibling().getPreviousSibling().getTextContent());
            System.out.println(" Publish date: " + nodeList.item(i).getLastChild().getPreviousSibling().
                    getPreviousSibling().getPreviousSibling().getTextContent());
            System.out.println(" Description: " + nodeList.item(i).getLastChild().getPreviousSibling().getTextContent());
            System.out.print("\n");
        }
    }

}

