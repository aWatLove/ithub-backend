package ru.chn.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.chn.dto.ProjectPatchDTO;
import ru.chn.dto.ProjectTeamPreviewDTO;
import ru.chn.dto.UserPreviewDTO;
import ru.chn.dto.request.PatchCreateRequest;
import ru.chn.dto.request.ProjectPostRequest;
import ru.chn.dto.response.*;
import ru.chn.model.*;
import ru.chn.repository.*;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository repo;
    private final TeamRepository teamRepo;
    private final ProjectTagRepository projectTagRepo;
    private final TagRepository tagRepo;
    private final UserProjectFolowsRepository upFolowsRepo;
    private final UserProjectLikesRepository upLikesRepo;
    private final UserRepository userRepo;
    private final PatchRepository patchRepo;
    private final UsersPatchesLikesRepository uPatchLikesRepo;

    // это очень плохо, можно сделать лучше и правильней, но времени мало
    public ProjectDetailsResponse getProjectById(Long id) {
        Project project = repo.findById(id).orElse(null);
        if (project == null) throw new EntityNotFoundException();
        Team team = teamRepo.findTeamById(project.getTeamId()).orElse(null);
        if (team == null) throw new EntityNotFoundException();

        ProjectDetailsResponse pdr = new ProjectDetailsResponse(project.getId(), project.getTitle(),
                project.getDescription(), project.getHtmlInfo(), project.getStack(),
                project.getLikesCount(), project.getFolowersCount(), project.getPatchCount(),
                project.getCreatedAt(), project.getUpdatedAt());

        ProjectTeamPreviewDTO ptpd = new ProjectTeamPreviewDTO(team.getId(), team.getName(), team.getAvatar());
        pdr.setTeam(ptpd);

        List<ProjectsTags> projectsTags = projectTagRepo.findAllByProjectId(id);
        for (ProjectsTags pt : projectsTags) {
            Tag tag = tagRepo.findById(pt.getTagId()).orElse(null);
            if (tag == null) continue;
            pdr.getTags().add(tag);
        }

        return pdr;
    }

    public ProjectDetailsResponse getProjectById(Long id, Long userId) {
        ProjectDetailsResponse pdr = getProjectById(id);
        if (upFolowsRepo.existsByUserIdAndProjectId(userId, id)) {
            pdr.setFolow(true);
        }
        if (upLikesRepo.existsByUserIdAndProjectId(userId, id)) {
            pdr.setLiked(true);
        }
        return pdr;
    }

    public ProjectListResponse getAllProjects(Long[] tagsList) {

        ProjectListResponse plr = new ProjectListResponse();
        plr.setProjects(new ArrayList<>());
        List<Long> projectIds = repo.findAllProjectIds();
        for (Long id : projectIds) {
            ProjectDetailsResponse pd = getProjectById(id);
            if (tagsList != null) {
                for (Long tagId : tagsList) {
                    if (projectTagRepo.existsByProjectIdAndTagId(id, tagId)) {
                        plr.getProjects().add(pd);
                    }
                }
                continue;
            }
            plr.getProjects().add(pd);
        }
        return plr;
    }

    public ProjectListResponse getAllProjects(Long[] tagsList, Long userId) {
        ProjectListResponse plr = getAllProjects(tagsList);
        for (ProjectDetailsResponse pdr : plr.getProjects()) {
            if (upFolowsRepo.existsByUserIdAndProjectId(userId, pdr.getId())) {
                pdr.setFolow(true);
            }
            if (upLikesRepo.existsByUserIdAndProjectId(userId, pdr.getId())) {
                pdr.setLiked(true);
            }
        }
        return plr;
    }

    public UsersLikesResponse getProjectLikes(Long id) {
        if (!repo.existsById(id)) throw new EntityNotFoundException();
        List<UserProjectLikes> upLikes = upLikesRepo.findAllByProjectId(id);
        UsersLikesResponse plr = new UsersLikesResponse();
        plr.setLikes(new ArrayList<>());
        for (UserProjectLikes up : upLikes) {
            User user = userRepo.findUserById(up.getUserId()).orElse(null);
            if (user == null) continue;
            UserPreviewDTO userPrevD = new UserPreviewDTO();
            userPrevD.setId(user.getId());
            userPrevD.setUsername(user.getUsername());
            userPrevD.setAvatar(user.getAvatar());
            plr.getLikes().add(userPrevD);
        }
        return plr;
    }

    public ProjectFolowersResponse getProjectFolowers(Long id) {
        if (!repo.existsById(id)) throw new EntityNotFoundException();
        List<UserProjectFolows> upFolows = upFolowsRepo.findAllByProjectId(id);
        ProjectFolowersResponse pfr = new ProjectFolowersResponse();
        pfr.setFolowers(new ArrayList<>());
        for (UserProjectFolows up : upFolows) {
            User user = userRepo.findUserById(up.getUserId()).orElse(null);
            if (user == null) continue;
            UserPreviewDTO userPrevD = new UserPreviewDTO();
            userPrevD.setId(user.getId());
            userPrevD.setUsername(user.getUsername());
            userPrevD.setAvatar(user.getAvatar());
            pfr.getFolowers().add(userPrevD);
        }
        return pfr;
    }

    public ProjectDetailsResponse createProject(ProjectPostRequest request, Long userId) {
        if (request.getTeamId() == null) throw new IllegalArgumentException("team id is null");
        if (request.getTitle() == null) throw new IllegalArgumentException("title is null");
        if (repo.existsByTeamIdAndTitle(request.getTeamId(), request.getTitle())) throw new EntityExistsException();
        if (!teamRepo.existsByOwnerIdAndId(userId, request.getTeamId())) throw new IllegalArgumentException();
        // todo это бы вынести в маппер
        Project project = new Project();
        project.setTeamId(request.getTeamId());
        project.setTitle(request.getTitle());
        project.setDescription(request.getDescription());
        project.setHtmlInfo(request.getHtmlInfo());
        project.setStack(request.getStack());
        LocalDateTime time = LocalDateTime.now();
        project.setOwnerId(userId);
        project.setCreatedAt(time);
        project.setUpdatedAt(time);
        project.setLikesCount(0L);
        project.setFolowersCount(1L);
        project.setPatchCount(0L);
        project = repo.saveAndFlush(project);
        if (request.getTags() != null) {
            for (Long tagId : request.getTags()) {
                Tag tag = tagRepo.findById(tagId).orElse(null);
                if (tag == null) continue;
                ProjectsTags pt = new ProjectsTags();
                pt.setProjectId(project.getId());
                pt.setTagId(tagId);
                projectTagRepo.save(pt);
            }
            projectTagRepo.flush();
        }
        UserProjectFolows upf = new UserProjectFolows();
        upf.setProjectId(project.getId());
        upf.setUserId(userId);
        upFolowsRepo.saveAndFlush(upf);
        return getProjectById(project.getId());
    }

    public Patch createPatch(Long projectId, PatchCreateRequest request, Long userId) {
        if (request.getTitle().isEmpty()) throw new IllegalArgumentException();
        if (projectId == null) throw new IllegalArgumentException();
        if (!repo.existsById(projectId)) throw new EntityNotFoundException();
        if (!repo.existsByOwnerIdAndId(userId, projectId)) throw new IllegalArgumentException();
        Project project = repo.findById(projectId).orElse(null);
        if (project == null) throw new EntityNotFoundException();
        project.setPatchCount(project.getPatchCount() + 1);
        repo.saveAndFlush(project);

        Patch patch = new Patch();
        patch.setCreatedAt(LocalDateTime.now());
        patch.setProjectId(projectId);
        patch.setTitle(request.getTitle());
        patch.setDescription(request.getDescription());
        patch.setLikesCount(0L);

        return patchRepo.saveAndFlush(patch);
    }

    public UsersLikesResponse getUserPatchLikes(Long projectId, Long patchId) {
        List<UsersPatchesLikes> usersPatchesLikes = uPatchLikesRepo.findAllByPatchId(patchId);
        UsersLikesResponse ulResp = new UsersLikesResponse();
        ulResp.setLikes(new ArrayList<>());

        for (UsersPatchesLikes upl : usersPatchesLikes) {
            User user = userRepo.findUserById(upl.getUserId()).orElse(null);
            if (user == null) continue;
            UserPreviewDTO userPrev = new UserPreviewDTO();
            userPrev.setId(user.getId());
            userPrev.setUsername(user.getUsername());
            userPrev.setAvatar(user.getAvatar());
            ulResp.getLikes().add(userPrev);
        }
        return ulResp;
    }

    public ProjectPatchesResponse getAllPatchByProjectId(Long projectId){
        if (!repo.existsById(projectId)) throw new EntityNotFoundException();
        List<Patch> patches = patchRepo.findAllByProjectId(projectId);
        List<ProjectPatchDTO> ppds = new ArrayList<>();
        for (Patch p : patches) {
            ppds.add(ProjectPatchDTO.build(p));
        }
        ProjectPatchesResponse resp = new ProjectPatchesResponse();
        resp.setPatches(ppds);
        return resp;
    }

    public ProjectPatchesResponse getAllPatchByProjectId(Long projectId, Long userId){
        ProjectPatchesResponse ppds = getAllPatchByProjectId(projectId);
        for (ProjectPatchDTO p : ppds.getPatches()) {
            if (uPatchLikesRepo.existsByUserIdAndPatchId(userId,p.getId())){
                p.setLiked(true);
            }
        }
        return ppds;
    }
}
