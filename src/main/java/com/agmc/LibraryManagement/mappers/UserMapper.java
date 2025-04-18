package com.agmc.LibraryManagement.mappers;

import com.agmc.LibraryManagement.model.dto.UserDTO;
import com.agmc.LibraryManagement.model.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDTO mapToDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .address(user.getAddress())
                .createdAt(user.getCreatedAt())
                .build();
    }

    public User mapToEntity(UserDTO userDTO) {
        return User.builder()
                .id(userDTO.getId())
                .name(userDTO.getName())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .address(userDTO.getAddress())
                .build();
    }
}
