package com.example.largexml.test;

import com.example.largexml.AnalyseRequest;
import com.example.largexml.AnalyseResult;
import com.example.largexml.LargeXML;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = LargeXML.class)
public class AnalyseRequestTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void analyseShouldReturnCorrectResponse() throws Exception {

        final String baseUrl = "http://localhost:" + port +"/analyze/";
        URI uri = new URI(baseUrl);
        AnalyseRequest analyseRequest = new AnalyseRequest();
        analyseRequest.setUrl("https://s3-eu-west-1.amazonaws.com/merapar-assessment/arabic-posts.xml");
        ResponseEntity<AnalyseResult> result = this.restTemplate.postForEntity(uri, analyseRequest,
                AnalyseResult.class);
        Assert.assertEquals(result.getStatusCodeValue(), 200);
        Assert.assertEquals(result.getBody().getDetails().getTotalPosts(), 80);
        Assert.assertEquals(result.getBody().getDetails().getTotalAcceptedPosts(), 7);
        Assert.assertEquals(result.getBody().getDetails().getAvgScore(), 3);
    }

    @Test
    public void greetingShouldReturnDefaultMessage() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/",
                String.class)).contains("Hello Large XML!");
    }
}