package sax;

import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class Main_SAX {

    public static void main(String[] args) {

        try {

            XMLReader xmlReader = XMLReaderFactory.createXMLReader();
            MyHandlerSAX handler = new MyHandlerSAX();
            InputSource inputSource = new InputSource(new FileReader("bookCatalog.xml"));

            xmlReader.setContentHandler(handler);
            xmlReader.parse(inputSource);

            List<Book> books = handler.getBooks();

            for (Book book : books) {
                if ((book.getPrice() > 10) && book.getPublish_date().after(new SimpleDateFormat("yyyy-mm-dd").parse("2005-12-31"))) {
                    System.out.println(book);
                }
            }

        } catch (SAXException | IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            Logger.getLogger(Main_SAX.class.getName()).log(Level.SEVERE, null, e);
        }

    }

}
