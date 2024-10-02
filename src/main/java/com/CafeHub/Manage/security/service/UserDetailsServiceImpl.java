package com.CafeHub.Manage.security.service;

import com.CafeHub.Manage.Admin.entity.Admin;
import com.CafeHub.Manage.Admin.repository.AdminRepositroy;
import com.CafeHub.Manage.security.dto.UserAuthDTO;
import com.CafeHub.Manage.security.dto.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AdminRepositroy adminRepositroy;

    // 로그인 요청이 들어오면 username, password가 들어 올텐데 해당 서비스에서는 시큐리티가 인증 작업을 할때
    // 사용자 정보를 검색하는걸 수행해줌, 즉 여기서 DB안에 있는 정보를 로그인 요청에 있는 username으로 조회하고
    // username으로 등록된 사용자가 있으면 들고가서 userDetals라는 DTO 에 담아서 검증로직에 던져줌
    // 그럼 거기서 role, username, password 등을 가져다 쓸 수 있음, 그래서 알아서 로그인을 처리해줌


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Admin admin = adminRepositroy.findByUsername(username);
        // 여기는 null 이반환된 경우 Nullpoint 터지면 오류가 필요할듯?
        UserAuthDTO userAuthDTO = new UserAuthDTO(admin.getUsername(), admin.getPassword(),admin.getRole());

        // usernamenotfound는 알아서 throws 되고 있기 때문에 username과 매치되는 멤버가 없음 오류는 필요 없을듯?
        return new UserDetailsImpl(userAuthDTO);
    }
}
