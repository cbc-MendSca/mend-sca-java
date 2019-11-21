package com.example.largexml;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.xml.sax.SAXException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

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
    public Result analyse(@RequestBody AnalyseRequest analyseRequest, final HttpServletRequest request, HttpServletResponse response) throws Exception {
        URL url = new URL(analyseRequest.getUrl());
        Result result = null;

        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        try {
            InputStream is = url.openStream();
            SAXParser saxParser = saxParserFactory.newSAXParser();
            ReducePosts handler = new ReducePosts();
            saxParser.parse(is, handler);
            result = handler.getResult();
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

        return result;
    }
}