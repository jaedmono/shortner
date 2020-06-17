package com.shortener.jaedmono.controller;

import com.shortener.jaedmono.repository.UrlRepository;
import com.shortener.jaedmono.service.UrlShortenerService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(UrlShortenerController.class)
class UrlShortenerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UrlShortenerService urlShortenerService;

    @Test
    public void createShortedUrl_ShouldReturn_201() throws Exception {
        this.mockMvc.perform(post("/url/")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{\n" +
                        "  \"url\": \"https://www.google.com\"\n" +
                        "}"))
                .andExpect(status().isCreated());
    }

    @Test
    public void createShortedUrl_ShouldReturn_400() throws Exception {
        this.mockMvc.perform(post("/url/")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{\n" +
                        "  \"url\": \"\"\n" +
                        "}"))
                .andExpect(status().is4xxClientError());
    }

}
