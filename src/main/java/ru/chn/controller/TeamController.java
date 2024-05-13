package ru.chn.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

}
