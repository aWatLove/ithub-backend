package ru.chn.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.chn.dto.payment.request.claim.ClaimPostRequest;
import ru.chn.security.jwt.JwtUtils;
import ru.chn.service.ClaimService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/claim")
public class ClaimController {
    private final ClaimService claimService;

    @Autowired
    JwtUtils jwtUtils;

    //Post create claim
    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public ResponseEntity<?> createClaim(@RequestBody ClaimPostRequest body, HttpServletRequest request) {
        Long id = jwtUtils.getUserIdFromJwtToken(jwtUtils.extractJwtToken(request));
        return ResponseEntity.ok(claimService.createClaimByTeamIdAndUserId(body, id));
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public ResponseEntity<?> getAllUsersClaims(HttpServletRequest request) {
        Long id = jwtUtils.getUserIdFromJwtToken(jwtUtils.extractJwtToken(request));
        return ResponseEntity.ok(claimService.getAllUsersClaims(id));
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    public ResponseEntity<?> getAllTeamsClaims(@PathVariable Long id) {
        return ResponseEntity.ok(claimService.getAllTeamsClaims(id));
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/{id}/accept")
    public ResponseEntity<?> deleteResume(
            @PathVariable Long id,
            @Param("accept") Boolean accept,
            @Param("role") String role,
            HttpServletRequest request) {
        Long userId = jwtUtils.getUserIdFromJwtToken(jwtUtils.extractJwtToken(request));
        claimService.confrimClaim(id, accept, role, userId);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteClaim(@PathVariable Long id, HttpServletRequest request) {
        Long userId = jwtUtils.getUserIdFromJwtToken(jwtUtils.extractJwtToken(request));
        claimService.deleteClaimByClaimId(id, userId);
        return ResponseEntity.ok().build();
    }
}
