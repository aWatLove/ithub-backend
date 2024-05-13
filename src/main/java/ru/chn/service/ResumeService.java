package ru.chn.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.chn.dto.request.ResumePostRequest;
import ru.chn.model.Resume;
import ru.chn.repository.ResumeRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ResumeService {
    private final ResumeRepository repo;


    // post Resume
    public Optional<Resume> createResume(ResumePostRequest request){
        Resume resume = new Resume();
        resume.setTitle(request.getTitle());
        resume.setHtmlInfo(request.getHtmlInfo());
        resume = repo.save(resume);
        return Optional.ofNullable(resume);
    }
    // get all user's resumes
    public List<Resume> getAllUsersResumes(Long id){
        return repo.findResumesByUserId(id);
    }

    // get Resume by Resume id
    public Optional<Resume> getResumeByResumeId(Long resumeId){
        return repo.findById(resumeId);
    }

    // update resume

    // delete resume

}
