package com.example.largexml.test;

import com.example.largexml.AnalyzeRequest;
import com.example.largexml.AnalyzeResult;
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
public class AnalyzeRequestTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void analyzeShouldReturnCorrectResponse1() throws Exception {

        final String baseUrl = "http://localhost:" + port +"/analyze/";
        URI uri = new URI(baseUrl);
        AnalyzeRequest analyzeRequest = new AnalyzeRequest();
        analyzeRequest.setUrl("https://s3-eu-west-1.amazonaws.com/merapar-assessment/arabic-posts.xml");
        ResponseEntity<AnalyzeResult> result = this.restTemplate.postForEntity(uri, analyzeRequest,
                AnalyzeResult.class);
        Assert.assertEquals(200, result.getStatusCodeValue());
        Assert.assertEquals(1436891967757L, result.getBody().getDetails().getFirstPost().getTime());
        Assert.assertEquals(1442227612053L, result.getBody().getDetails().getLastPost().getTime());
        Assert.assertEquals(80, result.getBody().getDetails().getTotalPosts());
        Assert.assertEquals(7, result.getBody().getDetails().getTotalAcceptedPosts());
        Assert.assertEquals(3, result.getBody().getDetails().getAvgScore());
    }

    @Test
    public void analyzeShouldReturnCorrectResponse2() throws Exception {

        final String baseUrl = "http://localhost:" + port +"/analyze/";
        URI uri = new URI(baseUrl);
        AnalyzeRequest analyzeRequest = new AnalyzeRequest();
        analyzeRequest.setUrl("https://s3-eu-west-1.amazonaws.com/merapar-assessment/3dprinting-posts.xml");
        ResponseEntity<AnalyzeResult> result = this.restTemplate.postForEntity(uri, analyzeRequest,
                AnalyzeResult.class);
        Assert.assertEquals(200, result.getStatusCodeValue());
        Assert.assertEquals(1452620719963L, result.getBody().getDetails().getFirstPost().getTime());
        Assert.assertEquals(1457094622410L, result.getBody().getDetails().getLastPost().getTime());
        Assert.assertEquals(655, result.getBody().getDetails().getTotalPosts());
        Assert.assertEquals(102, result.getBody().getDetails().getTotalAcceptedPosts());
        Assert.assertEquals(3, result.getBody().getDetails().getAvgScore());
    }
}