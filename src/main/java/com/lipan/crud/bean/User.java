package com.lipan.crud.bean;

import javax.validation.constraints.Pattern;

public class User {
    private Integer userid;

    @Pattern(regexp="(^[a-zA-Z0-9_-]{4,16}$)|(^[\u2E80-\u9FFF]{2,5})"
    		,message="用户名必须是2-5位中文或者4-16位英文和数字的组合")
    private String username;
    @Pattern(regexp="(^[a-zA-Z0-9_-]{6,18}$)|(^[\\u2E80-\\u9FFF]{2,5})",
			message="密码必须是2-5位中文或者6-18位英文和数字的组合")
    private String password;

    private String type;

    @Override
	public String toString() {
		return "User [userid=" + userid + ", username=" + username + ", password=" + password + ", type=" + type + "]";
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(Integer userid, String username, String password, String type) {
		super();
		this.userid = userid;
		this.username = username;
		this.password = password;
		this.type = type;
	}

	public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }
}