package com.CafeHub.Manage.security.dto;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@RequiredArgsConstructor
public class UserDetailsImpl implements UserDetails {

    private final UserAuthDTO userAuthDTO;



    // 시큐리티에서 한 사용자는 여러가지의 권한 role을 가질 수 있는데 그 권한들을 arraylist로 반환 시키겠다는거
    // 즉, arraylist에 사용자 권한들 있는거 다 담아봐 하는 메소드임
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> collection = new ArrayList<>();

        collection.add(new GrantedAuthority() {

            @Override
            public String getAuthority() {

                return userAuthDTO.getRole();
            }
        });

        return collection;
    }

    @Override
    public String getPassword() {
        return userAuthDTO.getPassword();
    }

    @Override
    public String getUsername() {
        return userAuthDTO.getUsername();
    }

    
    
    
    
    
    
    
    // 이 아래는 계정을 lock 걸거나 이용 불가로 만드는 등등 다양한 작업을 하는 건데 이건 일단 안쓰니까 다 true로 함, 필수 implement때문에 있는거임
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
