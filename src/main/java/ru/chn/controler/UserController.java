package ru.chn.controler;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.chn.dto.response.MessageResponse;
import ru.chn.dto.response.UserDetailResponse;
import ru.chn.dto.response.UsersPreviewsResponse;
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
}
