package ru.chn.controler;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.chn.dto.request.ResumePostRequest;
import ru.chn.dto.request.ResumePutRequest;
import ru.chn.dto.response.ResumePreviewsResponse;
import ru.chn.security.jwt.JwtUtils;
import ru.chn.service.ResumeService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/resume")
public class ResumeController {
    private final ResumeService resumeService;

    @Autowired
    JwtUtils jwtUtils;

    //post Resume
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/")
    public ResponseEntity<?> createResume(@RequestBody ResumePostRequest body, HttpServletRequest request){
        Long id = jwtUtils.getUserIdFromJwtToken(jwtUtils.extractJwtToken(request));
        return ResponseEntity.ok(resumeService.createResume(body, id));
    }
    //get all Resumes
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/")
    public ResponseEntity<?> getAllResumes(HttpServletRequest request){
        ResumePreviewsResponse rpr = new ResumePreviewsResponse();
        Long id = jwtUtils.getUserIdFromJwtToken(jwtUtils.extractJwtToken(request));
        rpr.setResumes(resumeService.getAllUsersResumes(id));
        return ResponseEntity.ok(rpr);
    }

    //get Resume by Resume id
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    public ResponseEntity<?> getResumesByResumeId(@PathVariable Long id){
        return ResponseEntity.ok(resumeService.getResumeByResumeId(id));
    }

    // update Resume
    @PreAuthorize("isAuthenticated()")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateResume(@RequestBody ResumePutRequest body, @PathVariable Long id){
        return ResponseEntity.ok(resumeService.updateResumeById(body, id));
    }

    // delete Resume
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteResume(@PathVariable Long id){
        resumeService.deleteResumeByResumeId(id);
        return ResponseEntity.ok("Deleted");
    }

}
