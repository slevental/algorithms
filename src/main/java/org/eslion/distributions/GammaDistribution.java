package org.eslion.distributions;

import org.apache.commons.lang.Validate;
import org.apache.commons.math.MathException;
import org.apache.commons.math.distribution.GammaDistributionImpl;

/**
 * This class extends gamma distribution from
 * Apache commons {@link org.apache.commons.math.distribution.GammaDistribution}
 * and used to estimate distribution's parameters automatically
 */
class GammaDistribution implements Distribution {
    public static final int DEFAULT_SIZE = 0x10;

    private double[] values;
    private int offset = -1;
    private boolean dirty;
    private GammaDistributionImpl gammaDistribution;

    GammaDistribution(int size) {
        Validate.isTrue(size > 0, "Zero or negative size is not allowed here");
        this.values = new double[size];
    }

    GammaDistribution() {
        this(DEFAULT_SIZE);
    }

    public void add(double value) {
        dirty = true;
        checkSize(++offset);
        values[offset] = value;
    }

    public double probabilityWithConfidence(double value) throws MathException {
        double sd = Math.sqrt(getVariance());
        return getDistribution().cumulativeProbability(value - sd, value + sd);
    }

    private void checkSize(int offset) {
        if (values.length == offset) {
            double[] v = values;
            values = new double[values.length * 2];
            System.arraycopy(v, 0, values, 0, v.length);
        }
    }

    public double cumulativeProbability(double x) throws MathException {
        return getDistribution().cumulativeProbability(x);
    }

    public double cumulativeProbability(double x0, double x1) throws MathException {
        return getDistribution().cumulativeProbability(x0, x1);
    }

    private GammaDistributionImpl getDistribution() {
        return gammaDistribution = estimateDistribution();
    }

    private GammaDistributionImpl estimateDistribution() {
        if (!dirty)
            return gammaDistribution;
        dirty = false;

        //count alpha and beta
        double mean = getMean();
        double variance = getVariance();
        return new GammaDistributionImpl(Math.pow(mean, 2) / variance, variance / mean);
    }

    private double getMean() {
        double mean = 0;
        for (int i = 0; i < getSize(); i++) {
            mean += values[i];
        }
        return mean / getSize();
    }

    private double getVariance() {
        double variance = 0;
        double mean = getMean();
        for (int i = 0; i < getSize(); i++) {
            variance += Math.pow(values[i] - mean, 2);
        }
        return variance / getSize();
    }

    private int getSize() {
        return offset + 1; // offset points to last added element
    }
}
