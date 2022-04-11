package com.progressoft.tools;

import java.math.BigDecimal;

public class ScoringSummaryImp implements ScoringSummary {
    private BigDecimal mean, median, std, variance, min, max;
    @Override
    public BigDecimal mean() {
        return this.mean;
    }

    @Override
    public BigDecimal standardDeviation() {
        return this.std;
    }

    @Override
    public BigDecimal variance() {
        return this.variance;
    }

    @Override
    public BigDecimal median() {
        return this.median;
    }

    @Override
    public BigDecimal min() {
        return this.min;
    }

    @Override
    public BigDecimal max() {
        return this.max;
    }

    @Override
    public void setMax(BigDecimal max) {
        this.max = max;
    }

    @Override
    public void setMin(BigDecimal min) {
        this.min = min;
    }

    @Override
    public void setMedian(BigDecimal median) {
        this.median = median;
    }

    @Override
    public void setMean(BigDecimal mean) {
        this.mean = mean;
    }

    @Override
    public void setStandardDeviation(BigDecimal standardDeviation) {
        this.std = standardDeviation;
    }

    @Override
    public void setVariance(BigDecimal variance) {
        this.variance = variance;
    }

}