package org.springframework.samples.petclinic.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Causa;
import org.springframework.samples.petclinic.model.Donation;
import org.springframework.samples.petclinic.repository.DonationRepository;
import org.springframework.samples.petclinic.service.base.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DonationService extends BaseService<Donation> {

	private final DonationRepository donationRepository;
	private final CausaService causaService;

	@Autowired
	public DonationService(final DonationRepository donationRepository, final CausaService causaService) {
		this.donationRepository = donationRepository;
		this.causaService = causaService;
	}

	@Transactional(readOnly = true)
	public List<Donation> findByCausa(final int idCausa) {
		final Optional<Causa> causa = causaService.findById(idCausa);
		if (causa.isPresent()) {
			return donationRepository.findByCausaOrderByFechaDesc(causa.get());
		}
		return new ArrayList<>();
	}

	@Transactional(readOnly = true)
	public Donation findByCausaAndDonante(final Causa causa, final String donante) {
		final Optional<Causa> cause = causaService.findById(causa.getId());
		if (cause.isPresent()) {
			return donationRepository.findByCausaAndDonante(cause.get(), donante);
		}
		return null;
	}
}
