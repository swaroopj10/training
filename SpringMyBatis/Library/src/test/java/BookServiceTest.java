import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fidelity.business.Book;
import com.fidelity.services.BookService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:library-beans.xml")
class BookServiceTest {
	
	@Autowired
	BookService bookService;

	@Test
	void createsBookService() {
		assertNotNull(bookService);
	}
	
	@Test
	void bookServiceReturnsList() {
		List<Book> bookList = bookService.queryAllBooks();
		assertTrue(bookList.size() > 0);
	}
	
	@Test
	void bookServiceReturnsListByTitle() {
		List<Book> bookList = bookService.queryBookByTitle("Design Patterns");
		assertTrue(bookList.size() == 1);
	}
	
	@Test
	void bookServiceReturnsEmptyListByTitle() {
		List<Book> bookList = bookService.queryBookByTitle("Game of Thrones");
		assertTrue(bookList.size() == 0);
	}
}
