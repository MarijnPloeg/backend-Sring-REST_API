package nl.marijnploeg.kitereparatie.repository;

import nl.marijnploeg.kitereparatie.model.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserProfileDAS {

    private final FakeUserProfileDataStore fakeUserProfileDataStore;

    public UserProfileDAS(FakeUserProfileDataStore fakeUserProfileDataStore) {
        this.fakeUserProfileDataStore = fakeUserProfileDataStore;
    }

    public List<AppUser> getAppUsers() {
        return fakeUserProfileDataStore.getUserList();
    }
}
