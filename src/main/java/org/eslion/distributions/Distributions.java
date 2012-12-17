package org.eslion.distributions;

public final class Distributions {
    private Distributions(){}

    public static Distribution gamma(){
        return new GammaDistribution();
    }
}
