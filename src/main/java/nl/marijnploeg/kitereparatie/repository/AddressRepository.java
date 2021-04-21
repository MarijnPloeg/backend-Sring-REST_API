package nl.marijnploeg.kitereparatie.repository;

import nl.marijnploeg.kitereparatie.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
