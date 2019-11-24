
package com.example.largexml;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class AnalyzeHandler extends DefaultHandler {

    private AnalyzeResult analyzeResult = new AnalyzeResult();
    private AnalyzeResultDetails resultDetails = new AnalyzeResultDetails();
    private int totalScore = 0;
    private int totalScoreCount = 0;
    private StringBuilder data = null;

    public AnalyzeResult getAnalyzeResult() {
        return analyzeResult;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        data = new StringBuilder();
        if (qName.equalsIgnoreCase("row")) {
            Optional<Integer> acceptedAnswerId = parseInt(attributes.getValue("AcceptedAnswerId"));
            Optional<Integer> score = parseInt(attributes.getValue("Score"));
            Optional<Date> creationDate = parseDate(attributes.getValue("CreationDate"));

            resultDetails.setTotalPosts(resultDetails.getTotalPosts() + 1);

            if (acceptedAnswerId.isPresent() && acceptedAnswerId.get() > 0) {
                resultDetails.setTotalAcceptedPosts(resultDetails.getTotalAcceptedPosts() + 1);
            }

            totalScore += score.orElse(0);
            totalScoreCount += score.isPresent() ? 1 : 0;

            if (creationDate.isPresent()) {
                Date cd = creationDate.get();
                if (cd.compareTo(resultDetails.getFirstPost()) < 0) {
                    resultDetails.setFirstPost(cd);
                }
                if (cd.compareTo(resultDetails.getLastPost()) > 0) {
                    resultDetails.setLastPost(cd);
                }
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("posts")) {
            if (totalScoreCount > 0) {
                resultDetails.setAvgScore((totalScore + totalScoreCount / 2) / totalScoreCount);
            }
            analyzeResult.setDetails(resultDetails);
            analyzeResult.setAnalyseDate(new Date());
        }
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        data.append(new String(ch, start, length));
    }

    private Optional<Integer> parseInt(String a) {
        Optional<Integer> result = Optional.empty();
        try {
            result = Optional.of(Integer.parseInt(a));
        } catch (Exception e) {
        }
        return result;
    }

    private Optional<Date> parseDate(String a) {
        Optional<Date> result = Optional.empty();
        try {
            result = Optional.of(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").parse(a));
        } catch (Exception e) {
        }
        return result;
    }
}


