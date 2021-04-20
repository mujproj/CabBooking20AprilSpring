package com.cabbooking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cabbooking.entities.Cab;
import com.cabbooking.service.ICabService;

@RestController
@RequestMapping("/cab")
public class CabController {

	@Autowired
	private ICabService cabService;

	@PostMapping("/addCab")
	public Cab addCab(@RequestBody Cab cab) {
		return this.cabService.insertCab(cab);
	}

	@PutMapping("/updateCab/{id}")
	public Cab updateCab(@PathVariable("id") int id, @RequestBody Cab cab) {
		cab.setCabId(id);
		return this.cabService.updateCab(cab);
	}

	@GetMapping("viewCabOfType/{carType}")
	public List<Cab> viewCabOfType(@PathVariable("carType") String carType) {
		return this.cabService.viewCabsOfType(carType);
	}

	@GetMapping("countCabsOfType/{carType}")
	public int countCabsOfType(@PathVariable("carType") String carType) {
		return this.cabService.countCabsOfType(carType);
	}
}
