package com.danielkkelly.example.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Models a User Permission.
 */
@Entity
@Table(name = "security_permission")
public class SecurityPermission extends AbstractEntity<Long> {

    /** System identifier. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "security_permission_id")
    private Long id;

    /** The human readable name.*/
    @NotNull
    @Size(min = 3, max = 50)
    @Column(name = "security_permission_name")
    private String name;

    /** A description of the Permission. */
    @NotNull
    @Size(min = 3, max = 100)
    @Column(name = "security_permission_description")
    private String description;

    @ManyToMany(mappedBy = "securityPermissions")
    @RestResource(path = "roles", rel = "roles")
    List<SecurityRole> securityRoles;

    SecurityPermission() {}

    SecurityPermission(final String name, final String description) {
        this.name = name;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    @SuppressWarnings("unused")
    private void setId(final Long id) {
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

    public List<SecurityRole> getSecurityRoles() {
        return securityRoles;
    }

    public void setSecurityRoles(List<SecurityRole> securityRoles) {
        this.securityRoles = securityRoles;
    }
}