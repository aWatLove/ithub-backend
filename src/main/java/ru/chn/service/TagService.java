package ru.chn.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.chn.dto.other.tag.TagPreviewDTO;
import ru.chn.dto.payment.request.tag.TagPostRequest;
import ru.chn.model.Tag;
import ru.chn.repository.TagRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TagService {
    private final TagRepository repo;

    //get all tags
    public List<TagPreviewDTO> getAllTags() {
        List<Tag> tags = repo.findAll();

        return tags.stream()
                .map(this::convertToTagPreviewResponse)
                .collect(Collectors.toList());
    }


    //create tag
    public Tag createTag(TagPostRequest request) {
        Tag tag = new Tag();
        tag.setTagname(request.getTagname());
        tag = repo.saveAndFlush(tag);
        return tag;
    }

    //update tag
    public Tag updateTag(TagPostRequest request, Long id) {
        Tag tag = repo.findById(id).orElse(null);
        if (tag == null) throw new EntityNotFoundException();
        if (request.getTagname() != null) tag.setTagname(request.getTagname());
        tag = repo.save(tag);
        return tag;
    }

    //delete tag
    public void deleteTagByTagId(Long id) {
        repo.deleteById(id);
    }

    private TagPreviewDTO convertToTagPreviewResponse(Tag tag) {
        TagPreviewDTO response = new TagPreviewDTO();
        response.setId(tag.getId());
        response.setTagname(tag.getTagname());
        return response;
    }
}
