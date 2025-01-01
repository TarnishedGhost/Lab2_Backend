package tarnishedghost.web;

import tarnishedghost.dto.user.UserListDto;
import tarnishedghost.dto.user.request.RegisterRequestDto;
import tarnishedghost.service.UserService;
import tarnishedghost.service.mapper.UserMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@Validated
@Tag(name = "User")
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping
    public ResponseEntity<RegisterRequestDto> addUser(@Valid @RequestBody RegisterRequestDto user) {
        return ResponseEntity.ok(userMapper.toRegisterUserDto(userService.addUser(userMapper.toUser(user))));
    }

    @GetMapping
    public ResponseEntity<UserListDto> getAllUsers() {
        return ResponseEntity.ok(userMapper.toUserListDto(userService.getUsers()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegisterRequestDto> getUserById(@PathVariable UUID id) {
        return ResponseEntity.ok(userMapper.toRegisterUserDto(userService.getUserById(id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable UUID id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
