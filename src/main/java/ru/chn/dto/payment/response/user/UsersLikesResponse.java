package ru.chn.dto.payment.response.user;

import lombok.Getter;
import lombok.Setter;
import ru.chn.dto.other.user.UserPreviewDTO;

import java.util.List;

@Getter
@Setter
public class UsersLikesResponse {
    private List<UserPreviewDTO> likes;
}
