package com.kodilla.library.mapper;

import com.kodilla.library.domain.User;
import com.kodilla.library.domain.dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserMapper {

    private final BCryptPasswordEncoder passwordEncoder;

    public User mapToUser(UserDto userDto){
        return new User(
                userDto.getId(),
                userDto.getName(),
                userDto.getSurname(),
                userDto.getUsername(),
                passwordEncoder.encode(userDto.getPassword()),
                userDto.getAppUserRole(),
                userDto.getCreated()
        );
    }
    public UserDto mapToUserDto(User user){
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getSurname(),
                user.getUsername(),
                user.getPassword(),
                user.getAppUserRole(),
                user.getCreated()
        );
    }
    public List<UserDto> mapToUserDtoList(List<User> list){
        return list.stream()
                .map(this::mapToUserDto)
                .collect(Collectors.toList());
    }
}
