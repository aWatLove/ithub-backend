package ru.chn.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.chn.dto.request.ClaimPostRequest;
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
    public ResponseEntity<?> createClaim(@RequestBody ClaimPostRequest body, HttpServletRequest request){
        Long id = jwtUtils.getUserIdFromJwtToken(jwtUtils.extractJwtToken(request));
        return ResponseEntity.ok(claimService.createClaimByTeamIdAndUserId(body, id));
    }
}
