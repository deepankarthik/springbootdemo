package com.flowers.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.flowers.demo.modal.Detail;
import com.flowers.demo.service.DemoService;

@RestController()
@RequestMapping("/demo")
public class DemoController {
	
	@Autowired
	DemoService demoService;
	
	@PostMapping("/count")
	public @ResponseBody Integer count(@RequestBody List<Detail> details){
		return demoService.getCountOfUniqueUserIds(details);
	
	}

	@PostMapping("/updateDetail/{nthDetail}")
	public @ResponseBody List<Detail> updateDetail(@RequestBody List<Detail> details, @PathVariable Integer nthDetail){
		try {
			List<Detail> updatedDetails = demoService.updateDetail(details, nthDetail);
			return updatedDetails;
		}catch (IndexOutOfBoundsException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Detail Not Found", e);
		}
	}
	
	

}
