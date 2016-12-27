package com.kolyadko_polovtseva.book_maze.entity;

import java.io.Serializable;
import java.util.Set;

/**
 * Created by nadez on 11/27/2016.
 */
public class UserRole implements Serializable {

    public static final String ADMIN = "ADMIN";
    public static final String USER = "USER";

    private Long roleId;
    private String roleName;

    private Set<User> users;

    public UserRole() {
    }

    public UserRole(String roleName) {
        this.roleName = roleName;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
