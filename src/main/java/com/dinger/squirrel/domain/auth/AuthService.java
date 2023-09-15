package com.dinger.squirrel.domain.auth;

import com.dinger.squirrel.domain.auth.token.UserTokenService;
import com.dinger.squirrel.domain.auth.user.dto.User;
import com.dinger.squirrel.domain.auth.user.service.UserService;
import org.springframework.stereotype.Service;

import com.dinger.squirrel.domain.auth.oauth2.dto.OAuth2Provider;
import com.dinger.squirrel.domain.auth.oauth2.service.OAuth2Service;
import com.dinger.squirrel.domain.auth.oauth2.service.OAuth2ServiceFactory;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;
    private final UserTokenService userTokenService;
    private final OAuth2ServiceFactory oAuth2ServiceFactory;

    public String getAuthorizeUrl(OAuth2Provider oAuth2Provider) {
        final OAuth2Service oAuth2Service = oAuth2ServiceFactory.getOAuth2Service(oAuth2Provider);
        return oAuth2Service.getAuthorizeUrl();
    }

    public String getUserToken(OAuth2Provider oAuth2Provider, String code) {
        final OAuth2Service oAuth2Service = oAuth2ServiceFactory.getOAuth2Service(oAuth2Provider);
        final String accessToken = oAuth2Service.getAccessToken(code);
        final User user = oAuth2Service.getUser(accessToken);
        final Long id = userService.save(user);
        user.setId(id);

        return userTokenService.generateUtkn(user);
    }
}

