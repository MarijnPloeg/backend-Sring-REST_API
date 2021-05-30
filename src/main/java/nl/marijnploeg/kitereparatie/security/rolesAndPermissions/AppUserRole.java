package nl.marijnploeg.kitereparatie.security.rolesAndPermissions;

import com.google.common.collect.Sets;

import java.util.Set;

public enum AppUserRole {
    CUSTOMER("Klant"),
    EMPLOYEE("Werknemer"),
    MANAGER("Manager");

    private final String role;

    AppUserRole(String role) {
        this.role = role;
    }

}
