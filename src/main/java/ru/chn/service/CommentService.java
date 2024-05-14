package ru.chn.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.chn.dto.other.user.UserPreviewDTO;
import ru.chn.dto.payment.request.comment.CommentPostRequest;
import ru.chn.dto.payment.response.comment.CommentDetailResponse;
import ru.chn.dto.payment.response.comment.CommentGetAllResponse;
import ru.chn.model.Comment;
import ru.chn.model.User;
import ru.chn.repository.CommentRepository;
import ru.chn.repository.ProjectRepository;
import ru.chn.repository.UserRepository;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository repo;
    private final UserRepository userRepo;
    private final ProjectRepository projectRepo;

    public CommentDetailResponse create(CommentPostRequest request, Long projectId, Long userId, LocalDateTime time) {
        if (!projectRepo.existsById(projectId)) throw new EntityNotFoundException();
        User user = userRepo.findUserById(userId).orElse(null);
        if (user == null) throw new EntityNotFoundException();
        if (request.getText() == null) throw new IllegalArgumentException();
        UserPreviewDTO upd = new UserPreviewDTO();
        upd.setId(user.getId());
        upd.setUsername(user.getUsername());
        upd.setAvatar(user.getAvatar());
        Comment comment = new Comment(userId, projectId, time, request.getText());
        comment = repo.saveAndFlush(comment);
        return new CommentDetailResponse(comment.getId(), upd, comment.getText(), time);
    }

    public CommentGetAllResponse getAllComments(Long projectId) {
        if (!projectRepo.existsById(projectId)) throw new EntityNotFoundException();
        List<Comment> comments = repo.findAllByProjectId(projectId);
        CommentGetAllResponse resp = new CommentGetAllResponse();
        resp.setComments(new ArrayList<>());
        for (Comment comment : comments) {
            User user = userRepo.findUserById(comment.getUserId()).orElse(null);
            if (user == null) continue;
            UserPreviewDTO upd = new UserPreviewDTO();
            upd.setId(user.getId());
            upd.setUsername(user.getUsername());
            upd.setAvatar(user.getAvatar());
            resp.getComments().add(new CommentDetailResponse(comment.getId(), upd, comment.getText(), comment.getCreatedAt()));
        }

        return resp;
    }

    public void deleteComment(Long projectId, Long commentId, Long userId) {
        if (!projectRepo.existsById(projectId)) throw new EntityNotFoundException();
        if (!repo.existsByUserIdAndIdAndProjectId(userId, commentId, projectId)) throw new IllegalArgumentException();
        repo.deleteById(commentId);
    }
}
