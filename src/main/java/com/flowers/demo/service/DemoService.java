package com.flowers.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.flowers.demo.modal.Detail;

@Service
public class DemoService {
	
	@Value("${title}")
	private String title;
	
	@Value("${body}")
	private String body;
	
	public int getCountOfUniqueUserIds(List<Detail> details) {
		return details.stream().map(Detail::getUserId).collect(Collectors.toSet()).size();
	}

	
	public List<Detail> updateDetail(List<Detail> details, Integer nthDetail){
		
		int index = nthDetail-1;
		details.get(index).setBody(body);
		details.get(index).setTitle(title);
		return details;
	}
	
}
