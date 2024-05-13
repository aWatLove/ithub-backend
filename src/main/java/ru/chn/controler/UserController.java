package ru.chn.controler;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.chn.dto.response.MessageResponse;
import ru.chn.dto.response.UserDetailResponse;
import ru.chn.dto.response.UsersPreviewsResponse;
import ru.chn.service.UserService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        UserDetailResponse resp = userService.getUserDetailById(id);
        if (resp == null) {
            return ResponseEntity.badRequest().body(new MessageResponse("user not found with id: " + id));
        }

        return ResponseEntity.ok(resp);
    }


    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        UsersPreviewsResponse upr = new UsersPreviewsResponse();
        upr.setUsers(userService.getAllUsers());
        return ResponseEntity.ok(upr);
    }
}
