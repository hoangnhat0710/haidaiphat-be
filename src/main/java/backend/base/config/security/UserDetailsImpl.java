package backend.base.config.security;

import backend.base.domain.entity.User;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsImpl implements UserDetails {

    private static final long serialVersionUID = 1L;

    private Integer userId;

    private String username;

    private String email;

    @JsonIgnore
    private String password;

    private List<SimpleGrantedAuthority> authorities;

    public UserDetailsImpl(Integer userId, String username, String email, String password,
                           List<SimpleGrantedAuthority> authorities) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserDetailsImpl build(User user) {
        // For now, use a default role. You can add role field to User entity later
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("USER");
        List<SimpleGrantedAuthority> authorities = Collections.singletonList(authority);

        Integer userId = user.getId() != null ? user.getId().intValue() : null;
        String username = user.getUsername();
        String email = user.getEmail();
        String password = user.getPassword();

        return new UserDetailsImpl(userId, username, email, password, authorities);
    }

    @Override
    public List<SimpleGrantedAuthority> getAuthorities() {
        return authorities;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
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
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(userId, user.getUserId());
    }
}
