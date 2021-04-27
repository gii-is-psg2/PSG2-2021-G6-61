package org.springframework.samples.petclinic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Donation;
import org.springframework.samples.petclinic.repository.CausaRepository;
import org.springframework.samples.petclinic.repository.DonationRepository;
import org.springframework.samples.petclinic.service.base.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DonationService extends BaseService<Donation>{
	
	private DonationRepository donationRepository;
	private CausaRepository causaRepository;
	
	@Autowired
	public DonationService(DonationRepository donationRepository, CausaRepository causaRepository) {
		this.donationRepository = donationRepository;
		this.causaRepository = causaRepository;
	}

	@Transactional(readOnly = true)
	public List<Donation> findByCausa(int idCausa){
		return donationRepository.findByCausaOrderByFechaDesc(causaRepository.findById(idCausa).get());
	}
}
