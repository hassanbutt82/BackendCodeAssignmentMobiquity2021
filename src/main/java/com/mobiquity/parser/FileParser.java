package com.mobiquity.parser;


import com.mobiquity.exception.APIException;
import com.mobiquity.knapsack.IKnapsackSolution;
import com.mobiquity.knapsack.impl.KnapsackSolutionImpl;
import com.mobiquity.model.ParsedFileResult;
import com.mobiquity.model.Item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.mobiquity.utility.Constants.*;

public class FileParser {
    private static final IKnapsackSolution knapsackSolutionImpl = new KnapsackSolutionImpl();

public static ParsedFileResult fileParsing(String line) throws APIException{
    ParsedFileResult parsedFileResult =new ParsedFileResult();
    //split each line based on ':' delimiter.
    String[] textFileDivision = line.split(WEIGHT_SPLIT_DELIMITER);
    // only one ':' delimiter per line in the text file.
    if(textFileDivision.length==2){
        String[] rawItems = textFileDivision[1].split(SPACE_SPLITTER);
        String[] rawItemsImproved = Arrays.stream(rawItems)
                .filter(value ->
                        value != null && value.length() > 0
                )
                .toArray(size -> new String[size]);
        if(rawItemsImproved.length>0 && rawItemsImproved.length<=MAX_PACKAGE_ITEMS_COUNT){
            List<Item> parsedItemList=new ArrayList<>();
            for (String rawItemImproved : rawItemsImproved) {
                parsedItemList.add(parseItem(rawItemImproved));

            }
            if(!parsedItemList.isEmpty()){
                parsedFileResult.setPackageCapacity(Integer.parseInt(textFileDivision[0].trim()));
                parsedFileResult.setParsedItems(parsedItemList);
            }
            }
        else{
            throw new APIException("Package items can't be less than 1 nor greater than 15");
        }
    }
    else{
        throw new APIException("Each file line must have one ':' delimiter");
    }

    return parsedFileResult;
}

    /**
     * @param item validation for items within the package
     * @return
     * @throws APIException
     */
    private static Item parseItem(String item) throws APIException{

        String[] itemSplit = item.split(ITEM_SPLIT_DELIMITER);
        if (itemSplit.length != MAX_ITEMS_IN_PACKAGE) {
           // logger.error("Products are more than expected: {} products but expectation is {}", productSplit.length, MAX_PRODUCT_ATTR);
            throw new APIException("Invalid format for item in the file");
        }
        try {
            Integer index = Integer.parseInt(itemSplit[0].replace("(",""));
            double weight = Double.parseDouble(itemSplit[1]);
            Double cost = Double.parseDouble(itemSplit[2].split(CURRENCY)[1].replace(")",""));
            if (weight > MAX_PACKAGE_WEIGHT) {
               // logger.error("Weight is more than defined limit {}", MAX_PACKAGE_WEIGHT);
                throw new APIException("Weight can't be greater than 100");
            }
            if (cost > MAX_ITEM_COST) {
                throw new APIException("Cost can't be greater than 100");
            }
            return new Item(index, (int) weight, cost);
        } catch (NumberFormatException e) {
            //logger.error("Invalid format defined in file", e);
            throw new APIException("Invalid format for item in the file");
        }
    }
    /**
     * @param lines in raw-form in the text file
     * @return
     * @throws APIException
     */
    public static String ProcessFile(List<String> lines) throws APIException{
        List<String> result = new ArrayList<>();
        String finalOutput="";
        try {

            for (String fileLine : lines) {
                ParsedFileResult parsedFileResult = FileParser.fileParsing(fileLine);
                finalOutput = knapsackSolutionImpl.findCombination(parsedFileResult.getPackageCapacity(), parsedFileResult.getParsedItems());
                result.add(finalOutput);

            }
            finalOutput = String.join("\n", result);
            return finalOutput;
        }
        catch (APIException e){
            throw new APIException("Error while reading file");
        }


    }
}
