package com.ideage.ams.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class LoginUserDetails implements UserDetails {
    private final String displayName;
    private final String username;
    private final String password;
    private final String email;
    private final String department;

    private final String iconUrl;

    private final Collection<? extends GrantedAuthority> authorities;

    public LoginUserDetails(String username, String password, String email,
                            String department, Collection<? extends GrantedAuthority> authorities,
                            String inconUrl, String displayName) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.department = department;
        this.authorities = authorities;
        this.iconUrl = inconUrl;
        this.displayName = displayName;
    }

    // 必须实现的方法
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    // 其他必须方法...

    // 自定义的getter方法
    public String getEmail() {
        return email;
    }

    public String getDepartment() {
        return department;
    }

    public String getDisplayName() {
        return displayName;
    }
}
