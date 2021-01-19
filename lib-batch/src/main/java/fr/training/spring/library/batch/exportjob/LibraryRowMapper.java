package fr.training.spring.library.batch.exportjob;

import fr.training.spring.library.batch.dto.BookDto;
import fr.training.spring.library.batch.dto.LibraryDto;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class LibraryRowMapper implements RowMapper<LibraryDto> {
    @Override
    public LibraryDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        LibraryDto dto = new LibraryDto();
        dto.setId(rs.getLong("libid"));
        dto.setTypeLib(rs.getString("type_Lib"));
        //Adresse
        dto.setNumber(rs.getInt("number"));
        dto.setStreet(rs.getString("street"));
        dto.setCity(rs.getString("city"));
        dto.setPostalCode(rs.getInt("postal_code"));

        //Director
        dto.setName(rs.getString("name"));
        dto.setSurname(rs.getString("surname"));

        //Books
//id, b.title, b.author, b.pages_numbers, b.genre_litteraire, b.isbn
        BookDto bookDto =new BookDto();
        bookDto.setId(rs.getLong("id"));
        bookDto.setTitle(rs.getString("title"));
        bookDto.setAuthor(rs.getString("author"));
        bookDto.setPagesNumbers(rs.getInt("pages_numbers"));
        bookDto.setGenre(rs.getString("genre_litteraire"));
        bookDto.setIsbn(rs.getString("isbn"));
        dto.addBookDto(bookDto);

        return dto;
    }
}
