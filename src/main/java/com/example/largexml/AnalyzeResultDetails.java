package com.example.largexml;

import java.util.Date;

public class AnalyzeResultDetails {
    private Date firstPost = new Date(Long.MAX_VALUE);
    private Date lastPost = new Date(0);
    private int totalPosts = 0;
    private int totalAcceptedPosts = 0;
    private int avgScore = 0;

    public Date getFirstPost() {
        return firstPost;
    }

    public void setFirstPost(Date firstPost) {
        this.firstPost = firstPost;
    }

    public Date getLastPost() {
        return lastPost;
    }

    public void setLastPost(Date lastPost) {
        this.lastPost = lastPost;
    }

    public int getTotalPosts() {
        return totalPosts;
    }

    public void setTotalPosts(int totalPosts) {
        this.totalPosts = totalPosts;
    }

    public int getTotalAcceptedPosts() {
        return totalAcceptedPosts;
    }

    public void setTotalAcceptedPosts(int totoalAcceptedPosts) {
        this.totalAcceptedPosts = totoalAcceptedPosts;
    }

    public int getAvgScore() {
        return avgScore;
    }

    public void setAvgScore(int avgScore) {
        this.avgScore = avgScore;
    }
}
