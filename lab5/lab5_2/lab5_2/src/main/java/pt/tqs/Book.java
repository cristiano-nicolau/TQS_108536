package pt.tqs;

import java.util.Date;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class Book {
    private final String title;
	private final String author;
	private final Date published;
 
	// constructors, getter, setter ommitted

	public Book(String title, String author, LocalDateTime published) {
		this.title = title;
		this.author = author;
		this.published = Date.from(published.toInstant(ZoneOffset.UTC));
	}

	public String getAuthor() {
		return author;
	}
	public Date getPublished() {
		return published;
	}
	public String getTitle() {
		return title;
	}
}