package com.kodilla.library.enums;

import com.google.common.collect.Sets;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.kodilla.library.enums.Permissions.*;

public enum AppUserRole {
    USER(Sets.newHashSet(USER_PERMISSIONS)),
    ADMIN(Sets.newHashSet(ADMIN_PERMISSIONS,USER_PERMISSIONS)),
    SUPER_ADMIN(Sets.newHashSet(SUPER_ADMIN_PERMISSION,ADMIN_PERMISSIONS,USER_PERMISSIONS));

    private final Set<Permissions> permissions;

    AppUserRole(Set<Permissions> permissions) {
        this.permissions = permissions;
    }

    public Set<Permissions> getPermissions() {
        return permissions;
    }
    public Set<SimpleGrantedAuthority> getGrantedAuthority(){
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
        return permissions;
    }
}
