package com.kodilla.library.enums;

public enum Permissions {
    USER_PERMISSIONS("user"),
    ADMIN_PERMISSIONS("admin"),
    SUPER_ADMIN_PERMISSION("super_admin");

    private final String permission;

    Permissions(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
