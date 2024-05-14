package ru.chn.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.chn.dto.payment.request.project.PatchCreateRequest;
import ru.chn.dto.payment.request.project.ProjectPostRequest;
import ru.chn.dto.payment.request.project.ProjectPutRequest;
import ru.chn.security.jwt.JwtUtils;
import ru.chn.service.ProjectService;

import javax.servlet.http.HttpServletRequest;

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

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/{id}/patch")
    public ResponseEntity<?> createPatch(@PathVariable Long id, @RequestBody PatchCreateRequest body, HttpServletRequest request) {
        Long userId = jwtUtils.getUserIdFromJwtToken(jwtUtils.extractJwtToken(request));
        return ResponseEntity.ok(projectService.createPatch(id, body, userId));
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getProjectDetails(@PathVariable Long id, HttpServletRequest request) {
        if (jwtUtils.existAuthToken(request)) {
            Long userId = jwtUtils.getUserIdFromJwtToken(jwtUtils.extractJwtToken(request));
            return ResponseEntity.ok(projectService.getProjectById(id, userId));
        }
        return ResponseEntity.ok(projectService.getProjectById(id));
    }

    @GetMapping
    public ResponseEntity<?> getAllProjects(@Param("tags") Long[] tags, HttpServletRequest request) {
        if (jwtUtils.existAuthToken(request)) {
            Long userId = jwtUtils.getUserIdFromJwtToken(jwtUtils.extractJwtToken(request));
            return ResponseEntity.ok(projectService.getAllProjects(tags, userId));
        }
        return ResponseEntity.ok(projectService.getAllProjects(tags));
    }

    @GetMapping("/{id}/likes")
    public ResponseEntity<?> getProjectLikes(@PathVariable Long id) {
        return ResponseEntity.ok(projectService.getProjectLikes(id));
    }

    @GetMapping("/{projectId}/patch/{patchId}/likes")
    public ResponseEntity<?> getPatchLikes(@PathVariable Long projectId, @PathVariable Long patchId) {
        return ResponseEntity.ok(projectService.getUserPatchLikes(projectId, patchId));
    }

    @GetMapping("/{id}/folowers")
    public ResponseEntity<?> getProjectFolowers(@PathVariable Long id) {
        return ResponseEntity.ok(projectService.getProjectFolowers(id));
    }

    @GetMapping("/{projectId}/patch")
    public ResponseEntity<?> getAllPatchesByProjectId(@PathVariable Long projectId, HttpServletRequest request) {
        if (jwtUtils.existAuthToken(request)) {
            Long userId = jwtUtils.getUserIdFromJwtToken(jwtUtils.extractJwtToken(request));
            return ResponseEntity.ok(projectService.getAllPatchByProjectId(projectId, userId));
        }
        return ResponseEntity.ok(projectService.getAllPatchByProjectId(projectId));
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/{projectId}")
    public ResponseEntity<?> updateProjectDetails(@PathVariable Long projectId, @RequestBody ProjectPutRequest body, HttpServletRequest request) {
        Long userId = jwtUtils.getUserIdFromJwtToken(jwtUtils.extractJwtToken(request));
        return ResponseEntity.ok(projectService.updateProjectDetails(projectId, body, userId));
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/{projectId}/folow")
    public ResponseEntity<?> folowProject(@PathVariable Long projectId, HttpServletRequest request) {
        Long userId = jwtUtils.getUserIdFromJwtToken(jwtUtils.extractJwtToken(request));
        projectService.folowProject(projectId, userId);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/{projectId}/like")
    public ResponseEntity<?> likeProject(@PathVariable Long projectId, HttpServletRequest request) {
        Long userId = jwtUtils.getUserIdFromJwtToken(jwtUtils.extractJwtToken(request));
        projectService.likeProject(projectId, userId);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/{projectId}/patch/{patchId}/like")
    public ResponseEntity<?> likePatch(@PathVariable Long projectId, @PathVariable Long patchId, HttpServletRequest request) {
        Long userId = jwtUtils.getUserIdFromJwtToken(jwtUtils.extractJwtToken(request));
        projectService.likePatch(projectId, patchId, userId);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{projectId}")
    public ResponseEntity<?> deleteProject(@PathVariable Long projectId, HttpServletRequest request) {
        Long userId = jwtUtils.getUserIdFromJwtToken(jwtUtils.extractJwtToken(request));
        projectService.deleteProject(projectId, userId);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{projectId}/patch/{patchId}")
    public ResponseEntity<?> deleteProject(@PathVariable Long projectId, @PathVariable Long patchId, HttpServletRequest request) {
        Long userId = jwtUtils.getUserIdFromJwtToken(jwtUtils.extractJwtToken(request));
        projectService.deletePatch(projectId, userId);
        return ResponseEntity.ok().build();
    }
}
