package org.eslion.distributions;

import org.apache.commons.math.MathException;

/**
 * Common interface for distribution,
 * it extends generic distribution interface by adding
 * sample related methods
 */
public interface Distribution extends org.apache.commons.math.distribution.Distribution {

    /**
     * Add value as a distribution's random value
     *
     * @param value double value
     */
    void add(double value);

    /**
     * Predict value appears probability with standard deviation
     * confidence interval
     *
     * @param value double value to predict probability on
     * @return probability
     * @throws org.apache.commons.math.MathException may be rethrown from underlying commons lib
     */
    double probabilityWithConfidence(double value) throws MathException;
}
