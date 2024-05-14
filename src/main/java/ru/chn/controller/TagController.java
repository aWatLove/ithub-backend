package ru.chn.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.chn.dto.payment.request.tag.TagPostRequest;
import ru.chn.dto.payment.response.tag.TagPreviewsResponse;
import ru.chn.security.jwt.JwtUtils;
import ru.chn.service.TagService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tag")
public class TagController {
    private final TagService tagService;

    @Autowired
    JwtUtils jwtUtils;

    //get all tags
    @GetMapping
    public ResponseEntity<?> getAllTags() {
        TagPreviewsResponse tpr = new TagPreviewsResponse();
        tpr.setTags(tagService.getAllTags());
        return ResponseEntity.ok(tpr);
    }

    //create tag
    @PostMapping
    public ResponseEntity<?> createTag(@RequestBody TagPostRequest body) {
        return ResponseEntity.ok(tagService.createTag(body));
    }

    //update tag
    @PutMapping("/{id}")
    public ResponseEntity<?> updateTag(@RequestBody TagPostRequest body, @PathVariable Long id) {
        return ResponseEntity.ok(tagService.updateTag(body, id));
    }

    //delete tag
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTag(@PathVariable Long id) {
        tagService.deleteTagByTagId(id);
        return ResponseEntity.ok().build();
    }
}
