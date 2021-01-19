package fr.training.spring.library.infra.http;

import fr.training.spring.library.domain.book.IBookRepository;
import fr.training.spring.library.domain.book.GenreLitteraire;
import fr.training.spring.library.infra.http.dto.AuthorInfo;
import fr.training.spring.library.infra.http.dto.BookInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import fr.training.spring.library.domain.exception.ErrorCodes;
import fr.training.spring.library.domain.exception.NotFoundException;
import fr.training.spring.library.domain.exception.OpenLibraryTechnicalException;
import fr.training.spring.library.domain.book.Book;

@Component
public class BookExternalRepositoryImpl implements IBookRepository {

	private static final Logger logger = LoggerFactory.getLogger(BookExternalRepositoryImpl.class);

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public Book searchBook(final String isbn) {
		try {
			final ResponseEntity<BookInfo> response = restTemplate.getForEntity("/isbn/" + isbn + ".json",
					BookInfo.class);

			BookInfo bookInfo = response.getBody();

			logger.debug(bookInfo.toString());

			String authorName="";
			if(bookInfo.getAuthors() !=null){
				String authorKey = bookInfo.getAuthors().get(0).getKey();
				ResponseEntity<AuthorInfo> responseAuthor = restTemplate.getForEntity(authorKey + ".json",
						AuthorInfo.class);

				AuthorInfo authorInfo = responseAuthor.getBody();
				authorName = authorInfo.getName();
			}

			return Book.builder().isbn(isbn).title(bookInfo.getTitle()).author(authorName).pagesNumbers(bookInfo.getNumber_of_pages()).build();

		} catch (HttpClientErrorException | HttpServerErrorException e) {
			if (HttpStatus.NOT_FOUND.equals(e.getStatusCode())) {
				throw new NotFoundException("Book isbn " + isbn + " not found in openlibrary.org",
						ErrorCodes.BOOK_NOT_FOUND);
			}
			throw new OpenLibraryTechnicalException(e);
		}
		catch (Exception e){
			throw e;
		}
	}

}
