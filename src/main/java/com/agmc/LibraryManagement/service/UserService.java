package com.agmc.LibraryManagement.service;

import com.agmc.LibraryManagement.model.dto.UserDTO;
import com.agmc.LibraryManagement.model.entity.User;
import com.agmc.LibraryManagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    public final UserRepository userRepository;

    private UserDTO mapToDTO(User user) {
        return new UserDTO(user.getId(), user.getName(), user.getEmail(), user.getPassword(), user.getAddress());
    }

    private User mapToEntity(UserDTO userDTO) {
        return new User(userDTO.getId(), userDTO.getName(),userDTO.getEmail(), userDTO.getPassword(),userDTO.getAddress());
    }

    public UserDTO saveUser(UserDTO userDTO) {
        User user = mapToEntity(userDTO);
        User savedUser = userRepository.save(user);
        return mapToDTO(savedUser);
    }

    public List<UserDTO> getAllUsers(){
        return userRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public UserDTO getUserById(Long id) {
        return mapToDTO(userRepository.getReferenceById(id));
    }


    public UserDTO updateUser(UserDTO userDTO) {
        User user = mapToEntity(userDTO);
        userRepository.updateUser(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getAddress()
        );
        Optional<User> updatedUser = userRepository.findById(user.getId());
        return mapToDTO(updatedUser.get());
    }

    public List<UserDTO> searchUser(String value) {
        return userRepository.searchUser(value).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
