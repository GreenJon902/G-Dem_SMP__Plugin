package com.greenjon902.tabCompleterHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TabCompleterHelper {
    public static List<String> noSolutions = new ArrayList<String>();

    public static List<String> filter(List<String> allSolutions, String partialSolution) {
        ArrayList<String> filteredSolutions = new ArrayList<>();

        for (String solution : allSolutions) {
            if (solution.toLowerCase(Locale.ROOT).startsWith(partialSolution.toLowerCase(Locale.ROOT))) {
                filteredSolutions.add(solution);
            }
        }
        return filteredSolutions;
    }

    public static List<String> filterWithFunction(Object[] allSolutionsBeforeFunction, FilterWithFunctionFunction function, String partialSolution) {
        ArrayList<String> filteredSolutions = new ArrayList<>();

        for (Object solutionBeforeFunction : allSolutionsBeforeFunction) {
            String solution = function.convert(solutionBeforeFunction);
            if (solution.toLowerCase(Locale.ROOT).startsWith(partialSolution.toLowerCase(Locale.ROOT))) {
                filteredSolutions.add(solution);
            }
        }
        return filteredSolutions;
    }
}
