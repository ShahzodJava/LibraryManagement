package com.agmc.LibraryManagement.service;

import com.agmc.LibraryManagement.exception.ResourceNotFoundException;
import com.agmc.LibraryManagement.exception.UserAlreadyExistsException;
import com.agmc.LibraryManagement.mappers.UserMapper;
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

    private final UserRepository userRepository;
    private final UserMapper userMapper;


    public UserDTO saveUser(UserDTO userDTO) {
        User user = userMapper.mapToEntity(userDTO);
        User existedUser = userRepository.findByEmail(user.getEmail());
        if (existedUser!=null){
            throw new UserAlreadyExistsException("User already exists");
        }

        User savedUser = userRepository.save(user);

        return userMapper.mapToDTO(savedUser);
    }

    public List<UserDTO> getAllUsers(){
        return userRepository.findAll().stream()
                .map(userMapper::mapToDTO)
                .collect(Collectors.toList());
    }

    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("User not found with ID: " + id));
        return userMapper.mapToDTO(user);
    }


    public UserDTO updateUser(UserDTO userDTO) {
        User user = userMapper.mapToEntity(userDTO);
        userRepository.updateUser(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getAddress()
        );
        Optional<User> updatedUser = userRepository.findById(user.getId());
        return userMapper.mapToDTO(updatedUser.get());
    }

    public List<UserDTO> searchUser(String value) {
        return userRepository.searchUser(value).stream()
                .map(userMapper::mapToDTO)
                .collect(Collectors.toList());
    }

    public void deleteUser(Long id) {
        userRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("User not found with ID" + id));
        userRepository.deleteById(id);
    }
}
