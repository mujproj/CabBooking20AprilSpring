package com.cabbooking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cabbooking.entities.Cab;
import com.cabbooking.exception.CabNotFoundException;
import com.cabbooking.repository.ICabRepository;

@Service
@Transactional
public class CabService implements ICabService {

	@Autowired
	private ICabRepository cabRepository;
	
	@Override
	public Cab insertCab(Cab cab) {
		return this.cabRepository.save(cab);
	}

	@Override
	public Cab updateCab(Cab cab) {
		Optional<Cab> optionalCab = this.cabRepository.findById(cab.getCabId());
		if(optionalCab.isEmpty()) {
			throw new CabNotFoundException("No Cab Found with cab id " + cab.getCabId());
		}
		this.cabRepository.save(cab);
		return cab;
	}

	@Override
	public Cab deleteCab(Cab cab) {
		Optional<Cab> optionalCab = this.cabRepository.findById(cab.getCabId());
		if(optionalCab.isEmpty()) {
			throw new CabNotFoundException("No Cab Found with cab id " + cab.getCabId());
		}
		this.cabRepository.delete(cab);
		return cab;
	}

	@Override
	public List<Cab> viewCabsOfType(String carType) {
		List<Cab> cabsOfType = this.cabRepository.findByCarType(carType);
		if(cabsOfType.size() <= 0) {
			throw new CabNotFoundException("No Cab Found with carType " + carType);
		}
		return cabsOfType;
	}

	@Override
	public int countCabsOfType(String carType) {
		int count = this.cabRepository.countCabsOfType(carType);
		return count;
	}

}
