package com.agmc.LibraryManagement.mappers;

import com.agmc.LibraryManagement.model.dto.BookDTO;
import com.agmc.LibraryManagement.model.dto.UserDTO;
import com.agmc.LibraryManagement.model.entity.Book;
import com.agmc.LibraryManagement.model.entity.User;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {
    public BookDTO mapToDTO(Book book) {
        return BookDTO.builder()
                .id(book.getId())
                .name(book.getName())
                .author(book.getAuthor())
                .category(book.getCategory())
                .loans(book.getLoans())
                .isbn(book.getIsbn())
                .build();
    }

    public Book mapToEntity(BookDTO bookDTO) {
        return Book.builder()
                .id(bookDTO.getId())
                .name(bookDTO.getName())
                .author(bookDTO.getAuthor())
                .category(bookDTO.getCategory())
                .loans(bookDTO.getLoans())
                .isbn(bookDTO.getIsbn())
                .build();
    }
}
