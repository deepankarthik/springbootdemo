package com.flowers.demo.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.flowers.demo.modal.Detail;
import com.flowers.demo.util.TestUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoServiceTest {
	
	@Autowired
	DemoService demoService;
	
	@Test
	public void getCountOfUniqueIds_should_return_zero_given_empty_list() {
		List<Detail> details = new ArrayList<Detail>();
		int result = demoService.getCountOfUniqueUserIds(details);
	    assertEquals(0, result);
	}
	
	@Test
	public void getCountOfUniqueUserIds_should_return_count_given_loaded_list() {
		List<Detail> details = TestUtils.loadFromJsonFile();
		int result = demoService.getCountOfUniqueUserIds(details);
	    assertEquals(10, result);
	}
	
	
	@Test
	public void updateDetail_should_update_the_details_of_given_nthDetails() {
		List<Detail> details = TestUtils.loadFromJsonFile();
		List<Detail> updatedDetails = demoService.updateDetail(details, 4);
		
	    assertEquals(updatedDetails.get(3).getBody(), "1800Flowers");
	    assertEquals(updatedDetails.get(3).getTitle(), "1800Flowers");
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void updateDetail_should_not_update_the_details_of_given_nthDetails_not_exits() {
		List<Detail> details = TestUtils.loadFromJsonFile();
		demoService.updateDetail(details, 111);
	}
}
