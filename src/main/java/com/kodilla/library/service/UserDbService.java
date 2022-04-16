package com.kodilla.library.service;

import com.kodilla.library.domain.User;
import com.kodilla.library.domain.dao.UserDao;
import com.kodilla.library.domain.dto.UserDto;
import com.kodilla.library.exceptions.RoleNotFoundException;
import com.kodilla.library.exceptions.UserNotFoundException;
import com.kodilla.library.mapper.UserMapper;
import com.kodilla.library.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.kodilla.library.AppUserRole.*;

@Service
@RequiredArgsConstructor
public class UserDbService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserMapper mapper;
    private final UserDao dao;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
    public User saveUser(User user){
        return userRepository.save(user);
    }
    public void deleteUser(int id){
        userRepository.deleteById(id);
    }
    public User getById(int id) throws UserNotFoundException{
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }
    public List<User> getByNameContains(String contains){
        return userRepository.findByNameContains(contains);
    }
    public List<User> getBySurnameContains(String contains){
        return userRepository.findBySurnameContains(contains);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(()->new UsernameNotFoundException(
                        String.format("Username %s not found",username)));
    }

    public void signUp(UserDto userDto){
        boolean userExists = userRepository.findByUsername(userDto.getUsername()).isPresent();

        if(userExists){
            throw new IllegalStateException("Username is taken");
        }
        User user = mapper.mapToUser(userDto);
        userRepository.save(user);
    }

    public ResponseEntity<UserDto> changeRole(int userId, String role) throws UserNotFoundException, RoleNotFoundException {
        User user = dao.findById(userId).orElseThrow(UserNotFoundException::new);
        User saved;
        if (role.equalsIgnoreCase(USER.name())){
            saved = dao.save(new User(user.getId(),user.getName(),user.getSurname(),user.getUsername(),
                    user.getPassword(),USER));
            return ResponseEntity.ok(mapper.mapToUserDto(saved));

        }else if (role.equalsIgnoreCase(ADMIN.name())){
            saved = dao.save(new User(user.getId(),user.getName(),user.getSurname(),user.getUsername(),
                    user.getPassword(),ADMIN));
            return ResponseEntity.ok(mapper.mapToUserDto(saved));

        }else if (role.equalsIgnoreCase(SUPER_ADMIN.name())){
            saved = dao.save(new User(user.getId(),user.getName(),user.getSurname(),user.getUsername(),
                    user.getPassword(),SUPER_ADMIN));
            return ResponseEntity.ok(mapper.mapToUserDto(saved));
        }else{
            throw new RoleNotFoundException();
        }

    }
}
