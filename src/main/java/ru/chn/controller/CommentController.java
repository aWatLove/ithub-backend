package ru.chn.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.chn.dto.payment.request.comment.CommentPostRequest;
import ru.chn.security.jwt.JwtUtils;
import ru.chn.service.CommentService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/project")
public class CommentController {
    @Autowired
    JwtUtils jwtUtils;
    private final CommentService commentService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/{projectId}/comment")
    public ResponseEntity<?> createComment(@RequestBody CommentPostRequest body, @PathVariable Long projectId, HttpServletRequest request) {
        Long userId = jwtUtils.getUserIdFromJwtToken(jwtUtils.extractJwtToken(request));
        return ResponseEntity.ok(commentService.create(body, projectId, userId, LocalDateTime.now()));
    }

    @GetMapping("/{projectId}/comment")
    public ResponseEntity<?> getAllComments(@PathVariable Long projectId) {
        return ResponseEntity.ok(commentService.getAllComments(projectId));
    }


    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{projectId}/comment/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable Long projectId, @PathVariable Long commentId, HttpServletRequest request) {
        Long userId = jwtUtils.getUserIdFromJwtToken(jwtUtils.extractJwtToken(request));
        commentService.deleteComment(projectId, commentId, userId);
        return ResponseEntity.ok().build();
    }

}
