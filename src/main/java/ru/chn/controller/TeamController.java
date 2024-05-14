package ru.chn.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.chn.dto.payment.request.team.RoleMemberUpdateRequest;
import ru.chn.dto.payment.request.team.TeamSaveRequest;
import ru.chn.security.jwt.JwtUtils;
import ru.chn.service.TeamService;

import javax.servlet.http.HttpServletRequest;

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

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/{id}/member/{memberId}")
    public ResponseEntity<?> updateMemberRole(@RequestBody RoleMemberUpdateRequest body, @PathVariable Long id, @PathVariable Long memberId, HttpServletRequest request) {
        Long userId = jwtUtils.getUserIdFromJwtToken(jwtUtils.extractJwtToken(request));
        teamService.updateTeamMemberRole(id, userId, memberId, body);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/{id}/folow")
    public ResponseEntity<?> folowTeam(@PathVariable Long id, HttpServletRequest request) {
        Long userId = jwtUtils.getUserIdFromJwtToken(jwtUtils.extractJwtToken(request));
        teamService.folowTeam(id, userId);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{id}/member/{memberId}")
    public ResponseEntity<?> deleteTeamMember(@PathVariable Long id, @PathVariable Long memberId, HttpServletRequest request) {
        Long userId = jwtUtils.getUserIdFromJwtToken(jwtUtils.extractJwtToken(request));
        teamService.deleteMember(id, memberId, userId);
        return ResponseEntity.ok().build();
    }
}
