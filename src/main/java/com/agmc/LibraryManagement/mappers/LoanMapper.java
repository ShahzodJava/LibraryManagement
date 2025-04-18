package com.agmc.LibraryManagement.mappers;

import com.agmc.LibraryManagement.model.dto.BookDTO;
import com.agmc.LibraryManagement.model.dto.LoanDTO;
import com.agmc.LibraryManagement.model.entity.Book;
import com.agmc.LibraryManagement.model.entity.Loan;
import com.agmc.LibraryManagement.model.entity.User;
import org.springframework.stereotype.Component;

@Component
public class LoanMapper {
    public LoanDTO mapToDTO(Loan loan) {
        return LoanDTO.builder()
                .id(loan.getId())
                .userId(loan.getUser().getId())
                .bookId(loan.getBook().getId())
                .status(loan.getStatus())
                .loanDate(loan.getLoanDate())
                .returnDate(loan.getReturnDate())
                .build();
    }

    public Loan mapToEntity(LoanDTO loanDTO, User user, Book book) {
        return Loan.builder()
                .id(loanDTO.getId())
                .user(user)
                .book(book)
                .status(loanDTO.getStatus())
                .loanDate(loanDTO.getLoanDate())
                .returnDate(loanDTO.getReturnDate())
                .build();
    }
}
