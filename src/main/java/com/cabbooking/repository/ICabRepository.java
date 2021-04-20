package com.cabbooking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cabbooking.entities.Cab;
import com.cabbooking.exception.CabNotFoundException;

public interface ICabRepository extends JpaRepository<Cab, Integer> {
//	public Cab insertCab(Cab cab);
//	public Cab updateCab(Cab cab) throws CabNotFoundException;
//	public Cab deleteCab(Cab cab) throws CabNotFoundException;

	public List<Cab> findByCarType(String carType);

	@Query("select count(c) from Cab c where c.carType =:carType")
	public int countCabsOfType(@Param("carType") String carType) throws CabNotFoundException;
}