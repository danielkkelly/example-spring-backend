package com.danielkkelly.example.security;

import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import org.springframework.security.access.prepost.PreAuthorize;

@Retention(RUNTIME)
@PreAuthorize("hasAuthority('SCOPE_admin:user')")
public @interface UserAdminRequired {}