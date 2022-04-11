package com.progressoft.tools;

import java.util.ArrayList;
import java.util.Collections;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class Calculations {
    ArrayList < BigDecimal > column;
    BigDecimal median = new BigDecimal(0), mean = new BigDecimal(0), std = new BigDecimal(0), variance = new BigDecimal(0), min = new BigDecimal(0), max = new BigDecimal(0);
    ScoringSummaryImp scoringSummary = new ScoringSummaryImp();
    MathContext mc = new MathContext(7);

    public Calculations(ArrayList < BigDecimal > c) {
        this.column = c;
    }

    public void executeAll() {

        this.min();
        this.max();
        this.mean();
        this.median();
        this.variance();
        this.standardDiviaton();

    }

    public ScoringSummaryImp getScore() {

        return this.scoringSummary;

    }

    public void min() {

        this.min = Collections.min(this.column).setScale(2, RoundingMode.HALF_EVEN);
        this.scoringSummary.setMin(this.min);

    }

    public void max() {

        this.max = Collections.max(this.column).setScale(2, RoundingMode.HALF_EVEN);
        this.scoringSummary.setMax(this.max);
    }

    public void mean() {

        for (int i = 0; i < this.column.size(); i++) {

            this.mean = this.mean.add(this.column.get(i));
        }
        this.mean = this.mean.divide(BigDecimal.valueOf(this.column.size()), mc).setScale(0, RoundingMode.HALF_EVEN);

        this.mean = this.mean.setScale(2);

        this.scoringSummary.setMean(this.mean);
    }

    public void median() {

        ArrayList < BigDecimal > column = new ArrayList < > (this.column);

        Collections.sort(column);

        if (column.size() % 2 != 0) {

            this.median = column.get(column.size() / 2);
        } else {

            this.median = column.get((column.size() / 2) - 1).add(column.get(column.size() / 2)).divide(BigDecimal.valueOf(2));
        }
        this.median = this.median.setScale(2, RoundingMode.HALF_EVEN);

        this.scoringSummary.setMedian(this.median);
    }

    public void variance() {

        BigDecimal sum = new BigDecimal(0);
        BigDecimal numerator;

        for (int i = 0; i < this.column.size(); i++) {

            numerator = this.column.get(i).subtract(this.mean).pow(2);
            sum = sum.add(numerator);
        }

        this.variance = sum.divide(BigDecimal.valueOf(this.column.size()), mc);

        this.variance = this.variance.setScale(0, RoundingMode.HALF_EVEN).setScale(2);

        this.scoringSummary.setVariance(this.variance);
    }

    public void standardDiviaton() {

        this.std = BigDecimal.valueOf(Math.sqrt(this.variance.doubleValue()));
        this.std = this.std.setScale(2, RoundingMode.HALF_EVEN);

        this.scoringSummary.setStandardDeviation(this.std);
    }

}