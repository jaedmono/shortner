package com.shortener.jaedmono.controller;

import com.shortener.jaedmono.exception.EntityNotFoundException;
import com.shortener.jaedmono.service.UrlForwarderService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UrlForwarderController.class)
public class UrlForwarderControllerTest {


    public static final String LONG_URL = "www.google.com";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UrlForwarderService urlForwarderService;

    @Test
    public void redirectUrl_ShouldReturn_301() throws Exception {
        when(urlForwarderService.getLongUrl(anyString())).thenReturn(LONG_URL);
        this.mockMvc.perform(get("/url/uYbjWX")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(redirectedUrl(LONG_URL))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void createShortedUrl_ShouldReturn_500() throws Exception {
        when(urlForwarderService.getLongUrl(anyString())).thenThrow(EntityNotFoundException.class);
        this.mockMvc.perform(get("/uYbjWX")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is4xxClientError());
    }

}
