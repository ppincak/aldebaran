package com.aldebaran.auth.core.entity;

import com.aldebaran.data.domain.TrackableBaseDomain;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = User.tableName)
public class User extends TrackableBaseDomain {

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