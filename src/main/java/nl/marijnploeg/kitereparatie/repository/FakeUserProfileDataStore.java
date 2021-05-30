package nl.marijnploeg.kitereparatie.repository;

import nl.marijnploeg.kitereparatie.model.AppUser;
import nl.marijnploeg.kitereparatie.security.rolesAndPermissions.AppUserRole;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FakeUserProfileDataStore {

    private static final List<AppUser> USER_LIST = new ArrayList<>();

    static {
        USER_LIST.add(new AppUser("Marijn", "Ploeg", "marijnploeg@gmail.com", "password", AppUserRole.EMPLOYEE));
    }

    public List<AppUser> getUserList() {
        return USER_LIST;
    }
}
