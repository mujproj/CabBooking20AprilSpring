package com.cabbooking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cabbooking.entities.Driver;
import com.cabbooking.exception.DriverNotFoundException;

public interface IDriverRepository extends JpaRepository<Driver, Integer> {
//	public Driver insertDriver(Driver driver);
//	public Driver updateDriver(Driver driver)throws DriverNotFoundException;
//	public Driver deleteDriver(int driverId)throws DriverNotFoundException;
//	public Driver viewDriver(int driverId)throws DriverNotFoundException;

	public Driver findByUsername(String username);

	public Driver findByUsernameAndPassword(String username, String password);

	@Query("select d from Driver d where d.rating>=4.5f")
	public List<Driver> viewBestDrivers() throws DriverNotFoundException;
}