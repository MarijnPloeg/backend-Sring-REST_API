package nl.marijnploeg.kitereparatie.repository;

import nl.marijnploeg.kitereparatie.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    Boolean existsAddressByAppUsers(long appUserId);

    Address findAddressByAppUsers(long appUserId);
}
