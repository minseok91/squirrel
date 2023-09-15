package com.dinger.squirrel.domain.auth.oauth2.service;


import com.dinger.squirrel.domain.auth.user.dto.User;

public interface OAuth2Service {
    String getAuthorizeUrl();
    String getAccessToken(String code);
    User getUser(String accessToken);
}