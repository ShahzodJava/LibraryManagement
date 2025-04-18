package com.agmc.LibraryManagement.controller;

import com.agmc.LibraryManagement.model.dto.LoanDTO;
import com.agmc.LibraryManagement.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/loans")
@RequiredArgsConstructor
public class LoanController {

    private final LoanService loanService;

    @PostMapping("/create")
    public ResponseEntity<LoanDTO> createLoan(@RequestParam Long userId, @RequestParam Long bookId, @RequestBody LoanDTO loanDTO) {
        return ResponseEntity.ok(loanService.saveLoan(userId, bookId, loanDTO));
    }

    @GetMapping()
    public ResponseEntity<List<LoanDTO>> getAllLoans() {
        return ResponseEntity.ok(loanService.getAllLoan());
    }
}
