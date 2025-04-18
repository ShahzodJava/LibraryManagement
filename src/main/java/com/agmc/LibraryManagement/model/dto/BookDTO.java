package com.agmc.LibraryManagement.model.dto;

import com.agmc.LibraryManagement.model.entity.Loan;
import com.agmc.LibraryManagement.model.entity.User;
import com.agmc.LibraryManagement.model.enums.Category;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;


@Data
@Builder
public class BookDTO {

    private long id;

    @NotBlank(message = "Name must not be blank")
    private String name;

    @NotBlank(message = "Author cannot be blank")
    private String author;

    private List<Loan> loans;
    private String isbn;

    @NotNull(message = "Category should be specified")
    @Enumerated(value = EnumType.STRING)
    public Category category;

}

