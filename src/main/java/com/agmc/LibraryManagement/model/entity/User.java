package com.agmc.LibraryManagement.model.entity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;
    private String address;

    @OneToMany(mappedBy = "user")
    private List<Loan> loans;

    @Column(columnDefinition = "boolean default true")
    private boolean isActive;

    @CreationTimestamp
    @Column(name = "created", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated", nullable = true)
    private LocalDateTime updatedAt;


//    public User(Long id, String name, String email, String password, String address, List<Loan> loans, boolean isActive) {
//        this.id = id;
//        this.name = name;
//        this.email = email;
//        this.password = password;
//        this.address = address;
//        this.loans = loans;
//        this.isActive = isActive;
//        this.createdAt =LocalDateTime.now();
//    }
}
