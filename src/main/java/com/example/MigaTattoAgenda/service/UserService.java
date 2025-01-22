package com.example.MigaTattoAgenda.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MigaTattoAgenda.dto.UserInDto;
import com.example.MigaTattoAgenda.dto.UserOutDto;
import com.example.MigaTattoAgenda.entity.User;
import com.example.MigaTattoAgenda.errors.UserNotFoundException;
import com.example.MigaTattoAgenda.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public UserOutDto saveUser(UserInDto userInDto) {
        User user = userRepository.save(new User(userInDto.getUsername(), userInDto.getEmail()));

        return new UserOutDto(user.getUsername(), user.getEmail());
    }

    public void delete(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException();
        }

        userRepository.deleteById(id);
    }
}
