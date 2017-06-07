package com.aldebaran.auth.core.entity;

import com.aldebaran.data.domain.BaseDomain;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = User.tableName)
public class User extends BaseDomain {

    static final String tableName = "auth_user";

    @Column(name = "username",
            unique = true,
            nullable = false)
    private String username;

    @Column(name = "email",
            unique = true,
            nullable = false)
    private String email;

    @Column(name = "password",
            nullable = false)
    private String password;
    
    @Column(name = "temp_token_id",
            nullable = false)
    private String tempTokenId;

    @Type(type = "org.hibernate.type.NumericBooleanType")
    @Column(name = "enabled",
            nullable = false)
    private Boolean enabled;

    @Type(type = "org.hibernate.type.NumericBooleanType")
    @Column(name = "expired",
            nullable = false)
    private Boolean expired;

    @Type(type = "org.hibernate.type.NumericBooleanType")
    @Column(name = "locked",
            nullable = false)
    private Boolean locked;

    @Type(type = "org.hibernate.type.NumericBooleanType")
    @Column(name = "credentials_expired",
            nullable = false)
    private Boolean credentialsExpired;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTempTokenId() {
        return tempTokenId;
    }

    public void setTempTokenId(String tempTokenId) {
        this.tempTokenId = tempTokenId;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getExpired() {
        return expired;
    }

    public void setExpired(Boolean expired) {
        this.expired = expired;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public Boolean getCredentialsExpired() {
        return credentialsExpired;
    }

    public void setCredentialsExpired(Boolean credentialsExpired) {
        this.credentialsExpired = credentialsExpired;
    }
}