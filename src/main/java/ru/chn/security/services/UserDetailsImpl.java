package ru.chn.security.services;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.chn.model.User;

import java.util.Collection;
import java.util.Objects;

@Getter
@Setter
public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String username;
    private String avatar;
    private String firstname;
    private String lastname;
    @JsonIgnore
    private String password;
    private String bioInfo;
    private String email;
    private String telegram;
    private String link;

    public UserDetailsImpl(Long id, String username, String avatar,
                           String firstname, String lastname, String password,
                           String bioInfo, String email, String telegram, String link) {
        this.id = id;
        this.username = username;
        this.avatar = avatar;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.bioInfo = bioInfo;
        this.email = email;
        this.telegram = telegram;
        this.link = link;
    }

    public static UserDetailsImpl build(User user) {
        return new UserDetailsImpl(
                user.getId(), user.getUsername(), user.getAvatar(),
                user.getFirstname(), user.getLastname(), user.getPassword(),
                user.getBioInfo(), user.getEmail(), user.getTelegram(), user.getLink());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        UserDetailsImpl user = (UserDetailsImpl) obj;
        return Objects.equals(id, user.id);
    }
}
