package com.agmc.LibraryManagement.model.dto;

import com.agmc.LibraryManagement.model.entity.Book;
import com.agmc.LibraryManagement.model.entity.User;
import com.agmc.LibraryManagement.model.enums.LoanStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoanDTO {
    private long id;
    private Long userId;
    private Long bookId;
    private String loanDate;
    private String returnDate;
    private LoanStatus status;
}
