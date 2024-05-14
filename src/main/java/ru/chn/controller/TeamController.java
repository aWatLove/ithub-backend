package ru.chn.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.chn.dto.request.TeamSaveRequest;
import ru.chn.security.jwt.JwtUtils;
import ru.chn.service.TeamService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/team")
public class TeamController {

    private final TeamService teamService;

    @Autowired
    JwtUtils jwtUtils;

    @GetMapping("/{id}")
    public ResponseEntity<?> getTeamById(@PathVariable Long id, HttpServletRequest request) {
        if (jwtUtils.existAuthToken(request)) {
            Long userId = jwtUtils.getUserIdFromJwtToken(jwtUtils.extractJwtToken(request));
            return ResponseEntity.ok(teamService.getTeamById(id, userId));
        }
        return ResponseEntity.ok(teamService.getTeamById(id));
    }

    @GetMapping
    public ResponseEntity<?> getAllTeams(HttpServletRequest request) {
        if (jwtUtils.existAuthToken(request)) {
            Long userId = jwtUtils.getUserIdFromJwtToken(jwtUtils.extractJwtToken(request));
            return ResponseEntity.ok(teamService.getAllTeams(userId));
        }
        return ResponseEntity.ok(teamService.getAllTeams(null));
    }

    @GetMapping("/{id}/folowers")
    public ResponseEntity<?> getTeamFolowers(@PathVariable Long id) {
        return ResponseEntity.ok(teamService.getTeamFolowers(id));
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping()
    public ResponseEntity<?> createTeam(@RequestBody TeamSaveRequest body, HttpServletRequest request) {
        Long userId = jwtUtils.getUserIdFromJwtToken(jwtUtils.extractJwtToken(request));
        return ResponseEntity.ok(teamService.createTeam(userId, body));
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateTeam(@RequestBody TeamSaveRequest body, @PathVariable Long id, HttpServletRequest request) {
        Long userId = jwtUtils.getUserIdFromJwtToken(jwtUtils.extractJwtToken(request));
        return ResponseEntity.ok(teamService.updateTeam(userId, id, body));
    }
}
