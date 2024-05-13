package ru.chn.controler;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.chn.dto.request.ResumePostRequest;
import ru.chn.service.ResumeService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/resume")
public class ResumeControler {
    private final ResumeService resumeService;

    //post Resume
    @PostMapping
    public ResponseEntity<?> createResume(@RequestBody ResumePostRequest request){
        return ResponseEntity.ok(resumeService.createResume(request));
    }
    //get all Resumes
    @GetMapping
    public ResponseEntity<?> getAllResumes(@RequestBody String body, HttpServletRequest request){
        Long userId = null;
        return ResponseEntity.ok(resumeService.getAllUsersResumes(userId));
    }

    //get Resume by Resume id
    @GetMapping("/{id}")
    public ResponseEntity<?> getResumesByResumeId(@PathVariable Long id){
        return ResponseEntity.ok(resumeService.getResumeByResumeId(id));
    }

}
