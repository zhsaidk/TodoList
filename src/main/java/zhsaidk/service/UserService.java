package zhsaidk.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import zhsaidk.database.dto.UserCreateEditDto;
import zhsaidk.database.dto.UserDto;
import zhsaidk.database.entity.User;
import zhsaidk.database.repository.UserRepository;
import zhsaidk.mapper.forUser.UserCreateEditMapper;
import zhsaidk.mapper.forUser.UserReadMapper;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final UserCreateEditMapper userCreateEditMapper;
    private final UserReadMapper userReadMapper;

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public UserDto findById(Long id) {
        return userRepository.findById(id)
                .map(userReadMapper::map)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Transactional
    public User createUser(UserCreateEditDto userCreateEditDto) {
        return Optional.of(userCreateEditDto)
                .map(userCreateEditMapper::map)
                .map(userRepository::saveAndFlush)
                .orElseThrow(() -> new IllegalArgumentException("User could not be created"));
    }

    @Transactional
    public User updateUser(Long id, UserCreateEditDto userCreateEditDto) {
        return userRepository.findById(id)
                .map(user -> {
                    userCreateEditMapper.map(userCreateEditDto, user);
                    userRepository.save(user);
                    return user;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Transactional
    public boolean deleteUser(Long id) {
        return userRepository.findById(id)
                .map(user -> {
                    userRepository.deleteById(id);
                    return true;
                })
                .orElse(false);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                Set.of(user.getRole())
        );
    }


}
