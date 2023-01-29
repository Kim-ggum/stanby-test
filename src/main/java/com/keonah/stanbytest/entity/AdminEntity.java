package com.keonah.stanbytest.entity;

import com.keonah.stanbytest.util.RoleConverter;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "admin")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AdminEntity extends BaseEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long no; // 회원 번호

    @Column(length = 255, nullable = false)
    private String id; // 아이디, 이메일 형식

    @Column(length = 255, nullable = false)
    private String pw; // 비밀번호

    @Column(length = 30, nullable = false)
    private String name; // 이름

    @Convert(converter = RoleConverter.class)
    @Column(length = 30, nullable = false)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collect = new ArrayList<>();
        collect.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return getRole().toString();
            }
        });
        return collect;
    }

    @Override
    public String getPassword() {
        return getPw();
    }

    @Override
    public String getUsername() {
        return getId();
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
}
