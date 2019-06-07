package com.flowers.demo.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.flowers.demo.service.DemoService;
import com.flowers.demo.util.TestUtils;


@RunWith(SpringRunner.class)
@WebMvcTest(DemoController.class)
public class DemoControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private DemoService service;

    @Before
    public void setUp() throws Exception {
    }

	@Test
	public void should_invoke_getCountOfUniqueIds_when_count_details() throws Exception {
		
        given(service.getCountOfUniqueUserIds(Mockito.any())).willReturn(6);
        
        mvc.perform(post("/demo/count").contentType(MediaType.APPLICATION_JSON).content(TestUtils.loadByteArrayFromFile())).andExpect(status().isOk()).andExpect(jsonPath("$", is(6)));
        verify(service, VerificationModeFactory.times(1)).getCountOfUniqueUserIds(Mockito.any());
        reset(service);

	} 
	
	@Test
	public void should_invoke_updateDetails_when_updateDetails_called() throws Exception {
		
        given(service.updateDetail(Mockito.any(), Mockito.any())).willReturn(TestUtils.loadFromJsonFile());
        
        mvc.perform(post("/demo/updateDetail/4").contentType(MediaType.APPLICATION_JSON).content(TestUtils.loadByteArrayFromFile())).andExpect(status().isOk()).andExpect(jsonPath("$.[0].id", is(1)));
        verify(service, VerificationModeFactory.times(1)).updateDetail(Mockito.any(),Mockito.any());
        reset(service);

	} 
	
}
