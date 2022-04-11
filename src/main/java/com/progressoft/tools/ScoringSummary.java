package com.progressoft.tools;

import java.math.BigDecimal;

public interface ScoringSummary {

    public BigDecimal mean();

    public BigDecimal standardDeviation();

    public BigDecimal variance();

    public BigDecimal median();

    public BigDecimal min();

    public BigDecimal max();

    public void setMax(BigDecimal max);

    public void setMin(BigDecimal min);

    public void setMedian(BigDecimal median);

    public void setMean(BigDecimal mean);

    public void setStandardDeviation(BigDecimal standardDeviation);

    public void setVariance(BigDecimal variance);
}
