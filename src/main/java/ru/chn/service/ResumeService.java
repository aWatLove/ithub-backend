package ru.chn.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.chn.dto.ResumePreviewDTO;
import ru.chn.dto.UserPreviewDTO;
import ru.chn.dto.request.ResumePostRequest;
import ru.chn.dto.request.ResumePutRequest;
import ru.chn.dto.response.ResumePreviewsResponse;
import ru.chn.model.Resume;
import ru.chn.model.User;
import ru.chn.repository.ResumeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ResumeService {
    private final ResumeRepository repo;


    // post Resume
    public Resume createResume(ResumePostRequest request, Long id){
        Resume resume = new Resume();
        resume.setUserId(id);
        resume.setTitle(request.getTitle());
        resume.setHtmlInfo(request.getHtmlInfo());
        resume = repo.save(resume);
        return resume;
    }
    // get all user's resumes
    public List<ResumePreviewDTO> getAllUsersResumes(Long id){
        List<Resume> resumes = repo.findResumesByUserId(id);

        return resumes.stream()
                .map(this::convertToResumePreviewResponse)
                .collect(Collectors.toList());

    }

    // get Resume by Resume id
    public Optional<Resume> getResumeByResumeId(Long resumeId){
        return repo.findById(resumeId);
    }

    // update resume
    public Optional<Resume> updateResumeById(ResumePutRequest request, Long resumeId){
        Resume resume = repo.findById(resumeId).orElse(null);

        if(request.getTitle()!= null) resume.setTitle(request.getTitle());
        if(request.getHtmlInfo()!= null) resume.setHtmlInfo(request.getHtmlInfo());
        if(request.getEmail()!= null) resume.setEmail(request.getEmail());
        if(request.getTelegram()!= null) resume.setTelegram(request.getTelegram());
        if(request.getLink()!= null) resume.setLink(request.getLink());
        resume = repo.save(resume);
        return Optional.ofNullable(resume);
    }

    // delete resume
    public void deleteResumeByResumeId(Long id){
        repo.deleteById(id);
    }



    private ResumePreviewDTO convertToResumePreviewResponse(Resume resume) {
        ResumePreviewDTO response = new ResumePreviewDTO();
        response.setId(resume.getId());
        response.setUserId(resume.getUserId());
        response.setTitle(resume.getTitle());
        response.setHtmlInfo(resume.getHtmlInfo());
        response.setEmail(resume.getEmail());
        response.setTelegram(resume.getTelegram());
        response.setLink(resume.getLink());
        return response;
    }

}
