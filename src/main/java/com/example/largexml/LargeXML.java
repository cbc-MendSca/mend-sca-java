package com.example.largexml;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import java.io.InputStream;
import java.net.URL;

@RestController
@EnableAutoConfiguration
public class LargeXML {

    public static void main(String[] args) {
        SpringApplication.run(LargeXML.class, args);
    }

    @RequestMapping("/")
    String home() {
        return "Hello Large XML!";
    }

    @PostMapping(path= "/analyze", consumes = "application/json", produces = "application/json")
    public AnalyzeResult analyse(@RequestBody AnalyzeRequest analyzeRequest, final HttpServletRequest request, HttpServletResponse response) throws Exception {
        URL url = new URL(analyzeRequest.getUrl());
        AnalyzeResult analyzeResult = null;

        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        try {
            InputStream is = url.openStream();
            SAXParser saxParser = saxParserFactory.newSAXParser();
            AnalyzeHandler handler = new AnalyzeHandler();
            saxParser.parse(is, handler);
            analyzeResult = handler.getAnalyzeResult();
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

        return analyzeResult;
    }
}