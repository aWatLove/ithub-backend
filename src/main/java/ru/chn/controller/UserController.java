package ru.chn.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.chn.dto.payment.request.user.UserUpdateRequest;
import ru.chn.dto.payment.response.MessageResponse;
import ru.chn.dto.payment.response.user.UserDetailResponse;
import ru.chn.dto.payment.response.user.UsersPreviewsResponse;
import ru.chn.security.jwt.JwtUtils;
import ru.chn.service.UserService;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @Autowired
    JwtUtils jwtUtils;

    // get user details by id
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        UserDetailResponse resp = userService.getUserDetailById(id);
        if (resp == null) {
            return ResponseEntity.badRequest().body(new MessageResponse("user not found with id: " + id));
        }

        return ResponseEntity.ok(resp);
    }

    // get current user details by token
    @GetMapping("/cur")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> getUserCurrent(HttpServletRequest request) {
        Long userId = jwtUtils.getUserIdFromJwtToken(jwtUtils.extractJwtToken(request));
        UserDetailResponse resp = userService.getUserDetailById(userId);
        if (resp == null) {
            return ResponseEntity.badRequest().body(new MessageResponse("user not found with id: " + userId));
        }

        return ResponseEntity.ok(resp);
    }

    // get all users preview
    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        UsersPreviewsResponse upr = new UsersPreviewsResponse();
        upr.setUsers(userService.getAllUsers());
        return ResponseEntity.ok(upr);
    }

    @PutMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> updateUserinfo(@RequestBody UserUpdateRequest body, HttpServletRequest request) {
        Long userId = jwtUtils.getUserIdFromJwtToken(jwtUtils.extractJwtToken(request));
        return ResponseEntity.ok(userService.updateUserInfo(userId, body));
    }
}
