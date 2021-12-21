package com.greenjon902.tabCompleterHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TabCompleterHelper {
    static List<String> noSolutions = new ArrayList<String>();

    static List<String> filter(List<String> allSolutions, String typedSolution) {
        ArrayList<String> filteredSolutions = new ArrayList<>();

        for (String solution : allSolutions) {
            if (solution.toLowerCase(Locale.ROOT).startsWith(typedSolution.toLowerCase(Locale.ROOT))) {
                filteredSolutions.add(solution);
            }
        }
        return filteredSolutions;
    }
}
