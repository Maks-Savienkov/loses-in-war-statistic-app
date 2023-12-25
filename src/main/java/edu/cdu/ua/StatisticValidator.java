package edu.cdu.ua;

import static java.util.Objects.requireNonNull;

public class StatisticValidator {
    public void validate(String statistic) {
        checkStatisticIsPresent(statistic);
    }

    private void checkStatisticIsPresent(String statistic) {
        requireNonNull(statistic);

        if (statistic.isBlank()) {
            throw new IllegalArgumentException(
                    "Cant process empty statistic"
            );
        }
    }
}
