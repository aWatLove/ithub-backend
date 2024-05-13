package ru.chn.controler;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.chn.dto.request.ResumePostRequest;
import ru.chn.security.jwt.JwtUtils;
import ru.chn.service.ResumeService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/resume")
public class ResumeControler {
    private final ResumeService resumeService;

    @Autowired
    JwtUtils jwtUtils;
    //post Resume
    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public ResponseEntity<?> createResume(@RequestBody ResumePostRequest request){
        return ResponseEntity.ok(resumeService.createResume(request));
    }
    //get all Resumes
    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public ResponseEntity<?> getAllResumes(@RequestBody String body, HttpServletRequest request){
        Long userId = jwtUtils.getUserIdFromJwtToken(jwtUtils.extractJwtToken(request));
        return ResponseEntity.ok(resumeService.getAllUsersResumes(userId));
    }

    //get Resume by Resume id
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    public ResponseEntity<?> getResumesByResumeId(@PathVariable Long id){
        return ResponseEntity.ok(resumeService.getResumeByResumeId(id));
    }

}
