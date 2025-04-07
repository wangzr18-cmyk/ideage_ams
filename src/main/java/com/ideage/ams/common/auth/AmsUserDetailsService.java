package com.ideage.ams.common.auth;

import com.ideage.ams.dao.AdminUserDao;
import com.ideage.ams.entity.AdminUser;
import com.ideage.ams.entity.LoginUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Spring Security の認証用ユーザサービス
 */
@Service
public class AmsUserDetailsService implements UserDetailsService {

    /**
     * 社員サービス
     */
    @Autowired
    private AdminUserDao adminUserDao;

    /**
     *
     * @param username the username identifying the user whose data is required.
     * @return 認証ユーザ
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AdminUser user = adminUserDao.getAdminUserByUserName(username);
        return new LoginUserDetails(
                user.getUserName(),
                user.getPassword(),
                user.getEmail(),
                "ビジネスソリューション2", //TODO: need to get the department info from DB, modify later
                List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole())),
                user.getIconUrl(), // TODO: need to get the department info from DB, modify later
                user.getDisplayName()
        );
    }
}
