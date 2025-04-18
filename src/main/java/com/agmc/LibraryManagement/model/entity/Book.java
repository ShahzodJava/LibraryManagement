package com.agmc.LibraryManagement.model.entity;

import com.agmc.LibraryManagement.model.enums.Category;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String isbn;

    @Enumerated(value = EnumType.STRING)
    public Category category;

    @OneToMany(mappedBy = "book")
    private List<Loan> loans;

    @CreationTimestamp
    @Column(name = "created", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated", nullable = true)
    private LocalDateTime updatedAt;

}
