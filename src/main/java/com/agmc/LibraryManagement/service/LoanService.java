package com.agmc.LibraryManagement.service;

import com.agmc.LibraryManagement.exception.IlleganStateException;
import com.agmc.LibraryManagement.mappers.BookMapper;
import com.agmc.LibraryManagement.mappers.LoanMapper;
import com.agmc.LibraryManagement.mappers.UserMapper;
import com.agmc.LibraryManagement.model.dto.LoanDTO;
import com.agmc.LibraryManagement.model.entity.Book;
import com.agmc.LibraryManagement.model.entity.Loan;
import com.agmc.LibraryManagement.model.entity.User;
import com.agmc.LibraryManagement.model.enums.LoanStatus;
import com.agmc.LibraryManagement.repository.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@RequiredArgsConstructor
@Service
public class LoanService {
    private final LoanRepository loanRepository;
    private final BookService bookService;
    private final UserService userService;
    private final LoanMapper loanMapper;
    private final UserMapper userMapper;
    private final BookMapper bookMapper;

    public LoanDTO saveLoan(Long userId, Long bookId, LoanDTO loanDTO) {
        loanRepository.findLoanByUserAndBook(userId,bookId).ifPresent(existingLoan-> {
                    throw new IlleganStateException("User already has this book booked and not yet returned.");
                }
        );
        User user = userMapper.mapToEntity(userService.getUserById(userId));
        Book book = bookMapper.mapToEntity(bookService.getBookById(bookId));

        Loan loan = loanMapper.mapToEntity(loanDTO, user, book);

        if (loan.getLoanDate() == null) {
            loan.setLoanDate(String.valueOf(LocalDateTime.now()));
        }
        if (loan.getStatus() == null) {
            loan.setStatus(LoanStatus.BOOKED);
        }

        Loan savedLoan = loanRepository.save(loan);
        return loanMapper.mapToDTO(savedLoan);
    }

    public List<LoanDTO> getAllLoan() {
        return loanRepository.findAll().stream().map(loanMapper::mapToDTO).toList();
    }
}
