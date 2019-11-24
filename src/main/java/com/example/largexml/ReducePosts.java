
package com.example.largexml;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ReducePosts extends DefaultHandler {

    private Result result = new Result();
    private ResultDetails resultDetails = new ResultDetails();
    private StringBuilder data = null;

    private int totalScore = 0;

    public Result getResult() {
        return result;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        data = new StringBuilder();
        if (qName.equalsIgnoreCase("row")) {
            int acceptedAnswerId = parseInt(attributes.getValue("AcceptedAnswerId"));
            int score = parseInt(attributes.getValue("Score"));
            Date creationDate = parseDateTime(attributes.getValue("CreationDate"));

            resultDetails.setTotalPosts(resultDetails.getTotalPosts() + 1);
            if (acceptedAnswerId > 0) {
                resultDetails.setTotalAcceptedPosts(resultDetails.getTotalAcceptedPosts() + 1);
            }
            totalScore += score;

            if (creationDate.compareTo(resultDetails.getFirstPost()) < 0) {
                resultDetails.setFirstPost(creationDate);
            }

            if (creationDate.compareTo(resultDetails.getLastPost()) > 0) {
                resultDetails.setLastPost(creationDate);
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("posts")) {
            if (resultDetails.getTotalPosts() > 0) {
                resultDetails.setAvgScore((totalScore + resultDetails.getTotalPosts() / 2) / resultDetails.getTotalPosts());
            }
            result.setDetails(resultDetails);
            result.setAnalyseDate(new Date());
        }
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        data.append(new String(ch, start, length));
    }

    private int parseInt(String a) {
        if (a != null && !a.isEmpty()) {
            return Integer.parseInt(a);
        }
        return 0;
    }

    private Date parseDateTime(String a) {
        Date date = new Date(0);
        try {
            date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").parse(a);
        } catch (Exception e) {
//            e.printStackTrace();
        }
        return date;
    }


}


