package com.wang.config;


import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="server")
@EnableAutoConfiguration
public class Config {
    private String port;

	public String getPort() {
		return port;
	}
}