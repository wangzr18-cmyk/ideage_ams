package com.ideage.ams.common.auth;

import com.ideage.ams.dao.AdminUserDao;
import com.ideage.ams.entity.AdminUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public class AmsPersistentTokenRepository implements PersistentTokenRepository {
    @Autowired
    private  AdminUserDao adminUserDao;

    @Override
    public void createNewToken(PersistentRememberMeToken token) {
        AdminUser user = adminUserDao.getAdminUserByUserName(token.getUsername());

        if (user != null) {
            adminUserDao.updateUserToken(user.getId(), token.getTokenValue(), token.getSeries());
        }
    }

    @Override
    public void updateToken(String series, String tokenValue, Date lastUsed) {

        AdminUser user = adminUserDao.getAdminUserByUserName(series);

        if (user != null) {
            adminUserDao.updateUserToken(user.getId(), tokenValue, series);
        }
    }

    @Override
    public PersistentRememberMeToken getTokenForSeries(String seriesId) {
        AdminUser user = adminUserDao.getAdminUserByUserName(seriesId);
        if (user == null || user.getUserToken() == null) {
            return null;
        }

        return new PersistentRememberMeToken(
                user.getUserName(),
                seriesId,
                user.getUserToken(),
                new Date()
        );
    }


    @Override
    public void removeUserTokens(String username) {
        AdminUser user = adminUserDao.getAdminUserByUserName(username);

        if (user != null) {
            adminUserDao.updateUserToken(user.getId(), null, null);
        }
    }

    private String generateHashToken (String tokenValue) {
        return BCrypt.hashpw(tokenValue, BCrypt.gensalt());
    }
}
