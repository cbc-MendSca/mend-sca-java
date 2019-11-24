package com.example.largexml;

import java.util.Date;

public class AnalyzeResult {
    private Date analyseDate;
    private AnalyzeResultDetails details;

    public Date getAnalyseDate() {
        return analyseDate;
    }

    public void setAnalyseDate(Date analyseDate) {
        this.analyseDate = analyseDate;
    }

    public AnalyzeResultDetails getDetails() {
        return details;
    }

    public void setDetails(AnalyzeResultDetails details) {
        this.details = details;
    }
}
