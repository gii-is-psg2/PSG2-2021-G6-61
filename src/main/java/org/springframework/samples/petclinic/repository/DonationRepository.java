package org.springframework.samples.petclinic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.samples.petclinic.model.Causa;
import org.springframework.samples.petclinic.model.Donation;
import org.springframework.stereotype.Repository;

@Repository("donationRepository")
public interface DonationRepository extends JpaRepository<Donation, Integer>{
	
	public List<Donation> findByCausaOrderByFechaDesc(Causa causa);

}
