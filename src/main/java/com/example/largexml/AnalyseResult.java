package com.example.largexml;

import java.util.Date;

public class AnalyseResult {
    private Date analyseDate;
    private AnalyseResultDetails details;

    public Date getAnalyseDate() {
        return analyseDate;
    }

    public void setAnalyseDate(Date analyseDate) {
        this.analyseDate = analyseDate;
    }

    public AnalyseResultDetails getDetails() {
        return details;
    }

    public void setDetails(AnalyseResultDetails details) {
        this.details = details;
    }
}
