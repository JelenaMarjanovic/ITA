package xpath;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class Main_XPath {

    public static void main(String[] args) throws XPathExpressionException, FileNotFoundException {

        XPathFactory factory = XPathFactory.newInstance();
        XPath path = factory.newXPath();
        XPathExpression xPathExpression = path.compile("//book[price > 10][number(translate(publish_date, '-', '')) > 20051231]/@id | //book[price > 10][number(translate(publish_date, '-', '')) > 20051231]/*");

        File xmlDocument = new File("bookCatalog.xml");
        InputSource inputSource = new InputSource(new FileInputStream(xmlDocument));

        Object result = xPathExpression.evaluate(inputSource, XPathConstants.NODESET);
        NodeList nodeList = (NodeList) result;

        for (int i = 0; i < nodeList.getLength(); i++) {

            System.out.println(nodeList.item(i).getNodeName() + ": " + nodeList.item(i).getFirstChild().getNodeValue());

            if ((i + 1) % 7 == 0) {
                System.out.println();
            }

        }

    }

}
