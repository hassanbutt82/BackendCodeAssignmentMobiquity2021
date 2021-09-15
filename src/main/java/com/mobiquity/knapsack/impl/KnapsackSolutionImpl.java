package com.mobiquity.knapsack.impl;

import com.mobiquity.exception.APIException;
import com.mobiquity.knapsack.IKnapsackSolution;
import com.mobiquity.model.Item;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Implementation of knapsack 0-1 for finding the best combinations
 */
public class KnapsackSolutionImpl implements IKnapsackSolution {

    /**
     * @param capacity of package
     * @param items containing all the items with their index, weight, cost
     * @return
     * @throws Exception
     */
    @Override
    public String findCombination(int capacity, List<Item> items) throws APIException {

            int n = items.size() + 1;
            int w = capacity + 1;
            double[][] a = new double[n][w];
            /*
            sorting to entertain the requirement where we need to pick low weightage item if the cost of two items are same.
             */
        try {
            items.sort((i1, i2) -> i1.getWeight().compareTo(i2.getWeight()));

            for (int i = 1; i < n; i++) {
                Item item = items.get(i - 1);

                for (int j = 1; j < w; j++) {
                    if (item.getWeight() > j) {
                        a[i][j] = a[i - 1][j];
                    } else {
                        a[i][j] = Math.max(a[i - 1][j], a[i - 1][j - item.getWeight()] + item.getCost());
                    }
                }
            }

            Set<Integer> indexes = new HashSet<>();
            int j = capacity;
            double costSum = a[n - 1][w - 1];
            for (; j > 0 && a[n - 1][j - 1] == costSum; j--) {

                for (int i = n - 1; i > 0; i--) {
                    if (a[i][j] != a[i - 1][j]) {
                        indexes.add(items.get(i - 1).getIndex());
                        j -= items.get(i - 1).getWeight();
                    }
                }
            }
            return actualResponseFormat(indexes);
        }
        catch (Exception e){
            throw new APIException("Issue in finding the right combination for output.");
        }


    }
    /*
sorting and setting up the format of string for response
 */
    private static String actualResponseFormat(Set<Integer> indexes) {
        String outputCombination =
                indexes.stream()
                        .mapToInt(i -> i)
                        .sorted()
                        .mapToObj(index -> Integer.toString(index))
                        .collect(Collectors.joining(","));
        return outputCombination.isEmpty() ? "-" : outputCombination;
    }

}
