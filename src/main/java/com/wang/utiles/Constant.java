package com.wang.utiles;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by ming on 2016/10/24.
 * locations={} 可以引入其余的属性文件
 */
@Component
@ConfigurationProperties(prefix = "constant.wx")
public class Constant {
    
    private String appid;

	private String secret;

	private String jscode2session;

	private String token;
	
	private String send;
	
    public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getSecret() {
		return secret;
	}
	public void setSecret(String secret) {
		this.secret = secret;
	}
	public String getJscode2session() {
		return jscode2session;
	}
	public void setJscode2session(String jscode2session) {
		this.jscode2session = jscode2session;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getSend() {
		return send;
	}
	public void setSend(String send) {
		this.send = send;
	}
}