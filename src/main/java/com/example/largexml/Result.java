package com.example.largexml;

import java.util.Date;

public class Result {
    private Date analyseDate;
    private ResultDetails details;

    public Date getAnalyseDate() {
        return analyseDate;
    }

    public void setAnalyseDate(Date analyseDate) {
        this.analyseDate = analyseDate;
    }

    public ResultDetails getDetails() {
        return details;
    }

    public void setDetails(ResultDetails details) {
        this.details = details;
    }
}
