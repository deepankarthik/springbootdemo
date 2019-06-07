package com.flowers.demo.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flowers.demo.modal.Detail;

public class TestUtils {

	public static byte[] loadByteArrayFromFile() {
		
		InputStream input;
		try {
			input = new FileInputStream("src/test/resources/details.json");
			return IOUtils.toByteArray(input);
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public static List<Detail> loadFromJsonFile() {
		ObjectMapper mapper = new ObjectMapper();
		List<Detail> details = new ArrayList<>();
		InputStream input;
		try {
			input = new FileInputStream("src/test/resources/details.json");
			details = mapper.readValue(input, new TypeReference<List<Detail>>(){});
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return details;
	}

}
