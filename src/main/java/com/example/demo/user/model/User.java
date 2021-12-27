package com.example.demo.user.model;

import com.example.demo.channel.model.Channel;
import com.example.demo.user.utils.UserRole;
import com.example.demo.userchannel.model.UserChannel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "app_user")
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @NonNull
    @Column(unique=true)
    private String username;

    @NonNull
    @Setter
    @JsonIgnore
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role = UserRole.USER;

    @Setter
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<UserChannel> userChannels;

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    // todo: expiration date
    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }


}
