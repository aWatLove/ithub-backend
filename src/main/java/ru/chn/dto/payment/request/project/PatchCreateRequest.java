package ru.chn.dto.payment.request.project;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatchCreateRequest {
    private String title;
    private String description;
}
