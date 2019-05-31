package com.douzone.df.payload;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;

import com.douzone.df.model.Role;

public class UserSummary {
    private Long id;
    private String username;
    private String name;
    private Collection<? extends GrantedAuthority> authorities;
    private String slackKey;
    private String slackChannel;
    public UserSummary(Long id, String username, String name) {
        this.id = id;
        this.username = username;
        this.name = name;
    }

    public UserSummary(Long id, String username, String name, Collection<? extends GrantedAuthority> authorities) {
    	this.id = id;
        this.username = username;
        this.name = name;
        this.authorities = authorities;
	}

	public UserSummary(Long id, String username, String name, Collection<? extends GrantedAuthority> authorities,
			String slackKey, String slackChannel) {
		this.id = id;
        this.username = username;
        this.name = name;
        this.authorities = authorities;
        this.slackKey = slackKey;
        this.slackChannel = slackChannel;
        
	}
	

	public String getSlackKey() {
		return slackKey;
	}

	public void setSlackKey(String slackKey) {
		this.slackKey = slackKey;
	}

	public String getSlackChannel() {
		return slackChannel;
	}

	public void setSlackChannel(String slackChannel) {
		this.slackChannel = slackChannel;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
