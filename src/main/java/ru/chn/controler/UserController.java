package ru.chn.controler;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.chn.dto.response.UsersPreviewsResponse;
import ru.chn.service.UserService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok("ok");
    }


    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        UsersPreviewsResponse upr = new UsersPreviewsResponse();
        upr.setUsers(userService.getAllUsers());
        return ResponseEntity.ok(upr);
    }
}
