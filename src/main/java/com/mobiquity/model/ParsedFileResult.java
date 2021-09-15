package com.mobiquity.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ParsedFileResult {

    private Integer packageCapacity;
    private List<Item> parsedItems;

    public ParsedFileResult() {

    }

    public Integer getPackageCapacity() {
        return packageCapacity;
    }

    public void setPackageCapacity(Integer packageCapacity) {
        this.packageCapacity = packageCapacity;
    }

    public List<Item> getParsedItems() {
        return parsedItems;
    }

    public void setParsedItems(List<Item> parsedItems) {
        this.parsedItems = parsedItems;
    }
}
