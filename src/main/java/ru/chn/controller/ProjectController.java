package ru.chn.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.chn.dto.request.ProjectPostRequest;
import ru.chn.security.jwt.JwtUtils;
import ru.chn.service.ProjectService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/project")
public class ProjectController {

    private final ProjectService projectService;

    @Autowired
    JwtUtils jwtUtils;


    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public ResponseEntity<?> createPost(@RequestBody ProjectPostRequest body, HttpServletRequest request) {
        Long userId = jwtUtils.getUserIdFromJwtToken(jwtUtils.extractJwtToken(request));
        return ResponseEntity.ok(projectService.createProject(body, userId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProjectDetails(@PathVariable Long id, HttpServletRequest request) {
        if (jwtUtils.existAuthToken(request)) {
            Long userId = jwtUtils.getUserIdFromJwtToken(jwtUtils.extractJwtToken(request));
            return ResponseEntity.ok(projectService.getProjectById(id,userId));
        }
        return ResponseEntity.ok(projectService.getProjectById(id));
    }

    @GetMapping
    public ResponseEntity<?> getAllProjects(@Param("tags") Long[] tags, HttpServletRequest request) {
        if (jwtUtils.existAuthToken(request)) {
            Long userId = jwtUtils.getUserIdFromJwtToken(jwtUtils.extractJwtToken(request));
            return ResponseEntity.ok(projectService.getAllProjects(tags,userId));
        }
        return ResponseEntity.ok(projectService.getAllProjects(tags));
    }

}
