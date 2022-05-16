package com.danielkkelly.example.domain.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.rest.core.annotation.RestResource;

/**
 * Models a User Role.
 */
@Entity
@Table(name = "security_role")
public class SecurityRole extends AbstractEntity<Long> {
    /** System identifier. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "security_role_id")
    private Long id;

    /** The human readable name.*/
    @NotNull
    @Size(min = 3, max = 50)
    @Column(name = "security_role_name")
    private String name;

    /** A description of the Role. */
    @NotNull
    @Size(min = 3, max = 100)
    @Column(name = "security_role_description")
    private String description;

    @ManyToMany(mappedBy = "securityRoles")
    private List<User> users;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "security_role_permission",
        joinColumns = @JoinColumn(name = "security_role_id", referencedColumnName = "security_role_id"),
        inverseJoinColumns = 
            @JoinColumn(name = "security_permission_id", referencedColumnName = "security_permission_id"))
    @RestResource(path = "permissions", rel = "permissions")
    List<SecurityPermission> securityPermissions;

    public Long getId() {
        return id;
    }
    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(final String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(final String description) {
        this.description = description;
    }

    public List<User> getUsers() {
        return users;
    }
    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<SecurityPermission> getSecurityPermissions() {
        return securityPermissions;
    }
    public void setSecurityPermissions(List<SecurityPermission> permissions) {
        this.securityPermissions = permissions;
    }
}