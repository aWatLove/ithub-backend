package ru.chn.dto.payment.response.claim;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ClaimGetAllResponse {
    private List<ClaimPostResponse> claims;
}
