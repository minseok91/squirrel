package com.dinger.squirrel.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Getter
@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties
@ConfigurationProperties("squirrel")
public class SquirrelConfig {
    private final List<String> allowedReferers;
}
