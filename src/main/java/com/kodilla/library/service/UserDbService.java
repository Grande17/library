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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.kodilla.library.enums.AppUserRole.*;

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
    public List<User> getByNameContains(String contains){
        return userRepository.findByNameContains(contains);
    }
    public List<User> getBySurnameContains(String contains){
        return userRepository.findBySurnameContains(contains);
    }


    public User getById(int id) throws UserNotFoundException, IllegalAccessError{
        Optional<User> loggedIn = userRepository.findByUsername(currentLoggedIn());
        Optional<User> userToFind = userRepository.findById(id);
        if (loggedIn.get().getId() == userToFind.get().getId() ||
        loggedIn.get().getAppUserRole().equals(ADMIN) || loggedIn.get().getAppUserRole().equals(SUPER_ADMIN)){
            return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        }else{
            throw new IllegalAccessError();
        }
    }

    public String currentLoggedIn(){
        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        }else{
            username = principal.toString();
        }
        return username;
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
    public ResponseEntity<UserDto> updateUserProcessor(UserDto userDto){
        Optional<User> loggedIn = userRepository.findByUsername(currentLoggedIn());
        Optional<User> userToUpdate = userRepository.findById(userDto.getId());
        if (loggedIn.get().getId() == userToUpdate.get().getId() ||
                loggedIn.get().getAppUserRole().equals(ADMIN) || loggedIn.get().getAppUserRole().equals(SUPER_ADMIN)){
            User user = mapper.mapToUser(userDto);
            User savedUser = userRepository.save(user);
            return ResponseEntity.ok(mapper.mapToUserDto(savedUser));
        }else{
            throw new IllegalAccessError();
        }
    }
}
