package edu.cdu.ua;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class LosesInWarParser {
    private final StatisticValidator statisticValidator = new StatisticValidator();

    LosesStatistic parseLosesStatistic(String input) {
        statisticValidator.validate(input);

        input = normaliseInput(input);

        try {
            input = recodeStringToUTF8(input);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        String[] statisticLines = removeBlank(input.split("\n"));
        if (statisticLines.length < 13) {
            return LosesStatistic.empty();
        }

        statisticLines = standardizeStatisticRecords(statisticLines);

        String[] counts = new String[13];
        for (int i = 0; i < statisticLines.length; i++) {
            String[] lineElements = statisticLines[i].split("\u2014");
            if (lineElements.length < 2) {
                counts[i] = "0";
                continue;
            }
            if (i == statisticLines.length - 1) {
                String[] lineElementParts = lineElements[1].trim().split(" ");
                counts[i] = lineElementParts[1].trim();
            } else {
                counts[i] = lineElements[1].trim().split(" ")[0];
            }
        }

        return LosesStatistic.newStatistic()
                .withTanks(Integer.parseInt(counts[0]))
                .withArmouredFightingVehicles(Integer.parseInt(counts[1]))
                .withCannons(Integer.parseInt(counts[2]))
                .withMultipleRocketLaunchers(Integer.parseInt(counts[3]))
                .withAntiAirDefenseDevices(Integer.parseInt(counts[4]))
                .withPlanes(Integer.parseInt(counts[5]))
                .withHelicopters(Integer.parseInt(counts[6]))
                .withDrones(Integer.parseInt(counts[7]))
                .withCruiseMissiles(Integer.parseInt(counts[8]))
                .withShipsOrBoats(Integer.parseInt(counts[9]))
                .withCarsAndTankers(Integer.parseInt(counts[10]))
                .withSpecialEquipment(Integer.parseInt(counts[11]))
                .withPersonnel(Integer.parseInt(counts[12]))
                .build();
    }

    private String normaliseInput(String input) {
        while (input.contains("<")) {
            int startTagIndex = input.indexOf("<");
            int finishTagIndex = input.indexOf(">");
            input = input.substring(0, startTagIndex) + input.substring(finishTagIndex + 1);
        }

        return input;
    }

    private String recodeStringToUTF8(String text) throws UnsupportedEncodingException {
        byte[] stringBytes = text.getBytes("windows-1251");
        return new String(stringBytes, StandardCharsets.UTF_8);
    }

    private String[] removeBlank(String[] split) {
        List<String> withoutBlank = new ArrayList<>();
        for (String s : split) {
            if (!s.isBlank()) {
                withoutBlank.add(s.trim());
            }
        }
        return withoutBlank.toArray(new String[withoutBlank.size()]);
    }

    private String[] standardizeStatisticRecords(String[] statisticRecords) {
        for (int i = 0; i < statisticRecords.length; i++) {
            if (!statisticRecords[i].contains("\u2014")) {
                statisticRecords[i - 1] += statisticRecords[i];
                statisticRecords = rewriteArrayWithoutElem(statisticRecords, i);
            }
        }

        return statisticRecords;
    }

    private String[] rewriteArrayWithoutElem(String[] array, int elemIndex) {
        String[] newArray = new String[array.length - 1];
        for (int i = 0, j = 0; j < newArray.length && i < array.length; i++) {
            if (i == elemIndex) {
                continue;
            }
            newArray[j++] = array[i];
        }

        return newArray;
    }
}
