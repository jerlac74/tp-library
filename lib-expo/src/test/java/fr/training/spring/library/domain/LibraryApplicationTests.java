package fr.training.spring.library.domain;

import fr.training.spring.library.appli.ILibraryService;
import fr.training.spring.library.domain.book.Book;
import fr.training.spring.library.domain.book.GenreLitteraire;
import fr.training.spring.library.domain.exception.ErrorCodes;
import fr.training.spring.library.domain.library.*;
import fr.training.spring.library.expo.dto.LibraryDto;
import fr.training.spring.library.expo.dto.MapperEntityToDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class LibraryApplicationTests {
	@Autowired
	private TestRestTemplate testRestTemplate;

	@Autowired
	private ILibraryRepository libraryRepository;
	@Autowired
	private ILibraryService libraryService;

	private static final String RESOURCE_URL ="/api/";

	private Book buildBook(long id, String title, String author, int pagesNumbers, GenreLitteraire genreLitteraire, String isbn){
		return Book.builder().id(id).title(title).author(author)
				.pagesNumbers(pagesNumbers)
				.genreLitteraire(genreLitteraire)
				.isbn(isbn)
				.build();
	}

	private List<Book> createBooks(){
		Book book1 = buildBook(-1L,"title1","Author1",15,GenreLitteraire.COMICS,"46-59-86");
		Book book2 = buildBook(-1L,"title2","Author2",169,GenreLitteraire.HISTORIQUE,"45-47-16");

		List<Book> books = new ArrayList<>();
		books.add(book1);
		books.add(book2);
		return books;
	}

	private Library buildLibrary(Long id, TypeLib typeLib, Address address, Director director, List<Book> books){
		return Library.builder().id(id).typeLib(typeLib).address(address).director(director).books(books).build();
	}

	@Test
	void should_getById(){
		long idToFind =1L;
		ResponseEntity<String> response = testRestTemplate.
				getForEntity(RESOURCE_URL + idToFind, String.class);

		if(response.getStatusCode()!=HttpStatus.OK){
			//création du user
			Address address =new Address(5,"rue du test", 75001, "Paris");
			Director director = new Director("testFisrtnameId","TestNameId");
			Library library = buildLibrary(1L,TypeLib.Scolaire,address,director,null);
			Library libraryAdded = libraryRepository.save(library);
			idToFind =libraryAdded.getId();
		}

		response = testRestTemplate.getForEntity(RESOURCE_URL + idToFind, String.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		System.out.println("findById :"+ response.getBody());
	}

	@Test
	void should_getAll(){
		ResponseEntity<String> response = testRestTemplate.
				getForEntity(RESOURCE_URL + "findall", String.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		System.out.println("findall: "+response.getBody());
	}

	@Test
	void should_findByType(){
		TypeLib typeLib = TypeLib.Universitaire;
		ResponseEntity<String> response = testRestTemplate.
				getForEntity(RESOURCE_URL + "findByType/"+typeLib.toString(), String.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		System.out.println("findByType :"+response.getBody());
	}

	@Test
	void should_findDirector(){
		ResponseEntity<String> response = testRestTemplate.
				getForEntity(RESOURCE_URL + "findDirector/Martin", String.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		System.out.println("findDirector :"+response.getBody());
	}

	@Test
	void should_searchDirectorJpql(){
		ResponseEntity<String> response = testRestTemplate.
				getForEntity(RESOURCE_URL + "searchDirectorJpql/Martin", String.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		System.out.println("searchDirectorJpql :"+ response.getBody());
	}

	@Test
	void should_searchDirectorNativeSql(){
		ResponseEntity<String> response = testRestTemplate.
				getForEntity(RESOURCE_URL + "searchDirectorNativeSql/Martin", String.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		System.out.println("searchDirectorNativeSql :"+ response.getBody());
	}

	@Test
	void should_create_wo_Books(){
		Address address =new Address(5,"rue du test", 75001, "Paris");
		Director director = new Director("testFirstnameWo","TestNameWo");
		Library library =buildLibrary(1L,TypeLib.Scolaire,address,director,null);

		LibraryDto libraryDto = MapperEntityToDto.mapToLibraryDto(library);

		ResponseEntity<String> response = testRestTemplate.
				postForEntity(RESOURCE_URL + "library", libraryDto , String.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		System.out.println("create Library :"+ response.getBody());
	}

	@Test
	void should_create_with_Books(){
		Address address =new Address(5,"rue du test", 75001, "Paris");
		Director director = new Director("testFirstname","TestName");
		List<Book> books = createBooks();
		Library library =  buildLibrary(1L,TypeLib.Scolaire,address,director,books);

		LibraryDto libraryDto = MapperEntityToDto.mapToLibraryDto(library);

		ResponseEntity<Long> response = testRestTemplate.
				postForEntity(RESOURCE_URL + "library", libraryDto , Long.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		System.out.println("create Library :"+ response.getBody());

		ResponseEntity<LibraryDto> responseLib = testRestTemplate.getForEntity(RESOURCE_URL +response.getBody(), LibraryDto.class);

		assertThat(responseLib.getStatusCode()).isEqualTo(HttpStatus.OK);
		System.out.println("findById after creation :"+ responseLib.getBody());

		/* crée une erreur en mode lazy
		Library lib= libraryService.findById(responseLib.getBody().getId());
		assertThat(lib).isNotNull();
		System.out.println("Library after findId :"+lib.toString());

		 */
	}

	@Test
	void should_delete(){
		testRestTemplate.delete(RESOURCE_URL + "delete/1",1);

		System.out.println("delete Library : 1");

		ResponseEntity<String> response = testRestTemplate.getForEntity(RESOURCE_URL + "1", String.class);


		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
		assertThat(response.getBody()).isEqualTo(ErrorCodes.LIBRARY_NOT_FOUND);
		System.out.println("findById :"+ response.getBody());
	}
}
