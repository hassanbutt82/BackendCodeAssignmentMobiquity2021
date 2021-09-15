package com.mobiquity.model;

import lombok.Data;


@Data
public class Item {
    private Integer index;
    private Integer weight;
    private Double cost;

    /**
     * @param index index for storing the index of package
     * @param weight weight for storing the item weight in the package
     * @param cost cost for storing the cost of the item in the package
     */
    public Item(Integer index, Integer weight, Double cost) {
        this.index = index;
        this.weight = weight;
        this.cost = cost;
    }

    public Integer getIndex() {
        return index;
    }

    public Integer getWeight() {
        return weight;
    }

    public Double getCost() {
        return cost;
    }


    @Override
    public String toString() {
        return "Item{" +
                "index=" + index +
                ", weight=" + weight +
                ", cost=" + cost +
                '}';
    }
}
