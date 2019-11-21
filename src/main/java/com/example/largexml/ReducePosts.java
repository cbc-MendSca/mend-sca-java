
package com.example.largexml;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ReducePosts extends DefaultHandler {

    private Post post = null;
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
            post = new Post();
            post.setId(parseInt(attributes.getValue("Id")));
            post.setPostTypeId(parseInt(attributes.getValue("PostTypeId")));
            post.setAcceptedAnswerId(parseInt(attributes.getValue("AcceptedAnswerId")));
            post.setCreationDate(parseDateTime(attributes.getValue("CreationDate")));
            post.setScore(parseInt(attributes.getValue("Score")));
            post.setViewCount(parseInt(attributes.getValue("ViewCount")));
            post.setBody(attributes.getValue("Body"));
            post.setOwnerUserId(parseInt(attributes.getValue("OwnerUserId")));
            post.setLastEditorUserId(parseInt(attributes.getValue("LastEditorUserId")));
            post.setLastEditDate(parseDateTime(attributes.getValue("LastEditDate")));
            post.setLastActivityDate(parseDateTime(attributes.getValue("LastActivityDate")));
            post.setTitle(attributes.getValue("Title"));
            post.setTags(attributes.getValue("Tags"));
            post.setAnswerCount(parseInt(attributes.getValue("AnswerCount")));
            post.setCommentCount(parseInt(attributes.getValue("CommentCount")));

            resultDetails.setTotalPosts(resultDetails.getTotalPosts() + 1);
            if (post.getAcceptedAnswerId() > 0) {
                resultDetails.setTotalAcceptedPosts(resultDetails.getTotalAcceptedPosts() + 1);
            }
            totalScore += post.getScore();

            if (post.getCreationDate().compareTo(resultDetails.getFirstPost()) < 0) {
                resultDetails.setFirstPost(post.getCreationDate());
            }

            if (post.getCreationDate().compareTo(resultDetails.getLastPost()) > 0) {
                resultDetails.setLastPost(post.getCreationDate());
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


