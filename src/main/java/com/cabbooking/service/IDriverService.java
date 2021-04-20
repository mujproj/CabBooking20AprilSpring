package com.cabbooking.service;

import java.util.List;

import com.cabbooking.dto.DriverAdd;
import com.cabbooking.dto.DriverReturn;
import com.cabbooking.dto.DriverUpdate;

public interface IDriverService {
	public DriverReturn insertDriver(DriverAdd addDriver);
	public DriverReturn updateDriver(DriverUpdate updateDriver);
	public DriverReturn deleteDriver(int driverId);
	public List<DriverReturn>viewBestDrivers();
	public DriverReturn viewDriver(int driverId);
	public DriverReturn validateDriver(String username, String password);
}