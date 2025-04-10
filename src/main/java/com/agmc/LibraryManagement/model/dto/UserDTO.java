package com.agmc.LibraryManagement.model.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
    private Long id;
    @Size(min = 2, max = 200)
    private String name;
    @NotBlank
//    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\\\.[A-Za-z]{2,6}$")
    private String email;
    @NotNull
    private String password;
    private String address;
}
