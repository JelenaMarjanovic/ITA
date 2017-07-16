package sax;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

class Book {

    private String id;
    private String author;
    private String title;
    private String genre;
    private double price;
    private Date publish_date;
    private String description;

    public Book(String id) {
        this.id = id;
    }

    public Book(String id, String author, String title, String genre, double price, Date publish_date, String description) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.genre = genre;
        this.price = price;
        this.publish_date = publish_date;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getPublish_date() {
        return publish_date;
    }

    public void setPublish_date(Date publish_date) {
        this.publish_date = publish_date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {

        Format formatter = new SimpleDateFormat("yyyy-MM-dd");
        String date = formatter.format(publish_date);

        return "Book id: " + id + "\nAuthor: " + author + "\nTitle: " + title + "\nGenre: " + genre + "\nPrice: "
                + String.valueOf(price) + "\nPublish date: " + date + "\nDescription: " + description + "\n";
    }

}
