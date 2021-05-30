package nl.marijnploeg.kitereparatie.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import nl.marijnploeg.kitereparatie.security.rolesAndPermissions.AppUserRole;
import org.junit.jupiter.api.Test;

public class AddressTest {
    @Test
    public void testAddUser() {
        Address address = new Address();
        AppUser appUser = new AppUser("Michiel", "Schipper", "michielschipper@hotmail.org", "password", AppUserRole.CUSTOMER);
        address.addUser(appUser);
        Address address1 = appUser.getAddress();
        assertSame(address, address1);
        assertEquals(1, address1.getAppUsers().size());
    }

    @Test
    public void testRemoveUser() {
        Address address = new Address();
        AppUser appUser = new AppUser("Martijn", "Schipper", "martijnschip@hotmail.org", "password", AppUserRole.CUSTOMER);
        address.removeUser(appUser);
        assertNull(appUser.getAddress());
        assertTrue(address.getAppUsers().isEmpty());
    }
}

