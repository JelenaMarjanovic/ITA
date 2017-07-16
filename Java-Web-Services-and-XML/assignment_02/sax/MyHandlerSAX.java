package sax;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MyHandlerSAX extends DefaultHandler {

    private Book book = null;
    private static List<Book> books = new ArrayList<Book>();
    private static final String[] elementTags = {"author", "title", "genre", "price", "publish_date", "description"};
    private static Set<String> tags = new HashSet<>(Arrays.asList(elementTags));
    private final StringBuilder cache = new StringBuilder();
    private final DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {

        cache.append(ch, start, length);

    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
        if ("book".equals(localName)) {
            book = new Book(atts.getValue(atts.getLocalName(0)));
        }

        cache.setLength(0);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (book != null && tags.contains(localName)) {
            String value = cache.toString();

            switch (localName) {
                case "author":
                    book.setAuthor(value);
                    break;
                case "title":
                    book.setTitle(value);
                    break;
                case "genre":
                    book.setGenre(value);
                    break;
                case "price":
                    book.setPrice(Double.parseDouble(value));
                    break;
                case "publish_date":
                    try {
                        book.setPublish_date(dateFormat.parse(value));
                    } catch (ParseException e) {
                        System.out.println("Error at date parsing:\n" + e.getMessage());
                    }
                    break;
                case "description":
                    book.setDescription(value);
                    break;
                default:
                    break;
            }
        } else if ("book".equals(localName)) {
            books.add(book);
            book = null;
        }

        cache.setLength(0);
    }

    public List<Book> getBooks() {
        return books;
    }

}
