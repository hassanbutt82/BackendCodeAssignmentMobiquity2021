package com.mobiquity.knapsack;

import com.mobiquity.exception.APIException;
import com.mobiquity.model.Item;

import java.util.List;

public interface IKnapsackSolution {
     String findCombination(int packageCapacity, List<Item> items) throws APIException;
}
