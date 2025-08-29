package edu.eci.stats;

import java.util.Collection;
import java.util.Objects;

public final class Stats {
    private Stats() {}

    /** Media aritmética. */
    public static double mean(Collection<Double> data) {
        Objects.requireNonNull(data, "data");
        if (data.isEmpty()) throw new IllegalArgumentException("Empty data set");

        return data.stream().mapToDouble(Double::doubleValue).average().orElse(Double.NaN);
    }

    /** Desviación estándar muestral (n-1). */
    public static double sampleStdDev(Collection<Double> data) {
        Objects.requireNonNull(data, "data");
        int n = data.size();
        if (n < 2) throw new IllegalArgumentException("At least 2 values required");

        double mean = mean(data);
        double sumSq = data.stream()
                .mapToDouble(Double::doubleValue)
                .map(x -> {
                    double d = x - mean;
                    return d * d;
                })
                .sum();

        return Math.sqrt(sumSq / (n - 1));
    }
}
