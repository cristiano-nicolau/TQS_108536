package pt.tqs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Format;

import io.cucumber.java.ParameterType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pt.tqs.Book;
import pt.tqs.Library;

public class BookSearchSteps {
    Library library = new Library();
	List<Book> result = new ArrayList<>();
 
    @ParameterType("([0-9]{4})-([0-9]{2})-([0-9]{2})")
    public LocalDateTime iso8601Date(String year, String month, String day) {
        return LocalDateTime.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day), 0, 0);
    }

    @Given("a book with the title {string}, written by {string}, published in {iso8601Date}")
    public void addBook(final String title, final String author, final LocalDateTime date) {   
        Book book = new Book(title, author, date);
        library.addBook(book);
    }

    @And("another book with the title {string}, written by {string}, published in {iso8601Date}")
    public void addBook2(final String title, final String author, final LocalDateTime date) {   
        Book book = new Book(title, author, date);
        library.addBook(book);
    }

    @When("the customer searches for books with the author {string}")
    public void the_customer_searches_for_books_with_the_author(String author) {
        result = library.findBooksByAuthor(author);
    }

    @When("the customer searches for books published between {int} and {int}")
    public void the_customer_searches_for_books_published_between_and(Integer year1, Integer year2) {
        LocalDateTime dt1 = LocalDateTime.of(year1,1,1,0,0);
        LocalDateTime dt2 = LocalDateTime.of(year2,1,1,0,0);
        Date date1 = Date.from(dt1.toInstant(ZoneOffset.UTC));
        Date date2 = Date.from(dt2.toInstant(ZoneOffset.UTC));
        result = library.findBooks(date1, date2);
    }

    @When("the customer searches for books with the title {string}")
    public void the_customer_searches_for_books_with_the_title(String title) {
        result = library.findBooksByTitle(title);
    }
 
    @Then("{int} books should have been found")
    public void book_should_have_been_found(Integer units) {
        assertEquals(units, result.size());
    }
 
    @Then("Book {int} should have the title {string}")
    public void book_should_have_the_title(Integer position, String title) {
        assertEquals(title, result.get(position - 1).getTitle());
    }

    @Then("Book {int} should have the author {string}")
    public void book_should_have_the_author(Integer position, String author) {
        assertEquals(author, result.get(position - 1).getAuthor());
    }

}
