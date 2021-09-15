package com.mobiquity.parser;

import com.mobiquity.exception.APIException;
import com.mobiquity.model.Item;
import com.mobiquity.model.ParsedFileResult;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileParserTest {
        @Test
        public void testConfigurationParser() throws APIException {
            /*
            Unit test for the testing of parsing functionality
             */
            ParsedFileResult expectedResult = new ParsedFileResult();

            expectedResult.setPackageCapacity(81);

            List<Item> items = new ArrayList<>();
            items.add(new Item(1,53,45.50));
            items.add(new Item(2,88,98.50));
            items.add(new Item(3,78,30.50));
            items.add(new Item(4,72,76.50));
            items.add(new Item(5,30,9.50));
            items.add(new Item(6,46,48.50));

            expectedResult.setParsedItems(items);

            ParsedFileResult finalResult = FileParser.fileParsing("81 : (1,53,€45.50) (2,88,€98.50) (3,78,€30.50) (4,72,€76.50) (5,30,€9.50) (6,46,€48.50)");

            assertEquals(expectedResult.getPackageCapacity(), finalResult.getPackageCapacity());
            assertEquals(expectedResult.getParsedItems().toString(), finalResult.getParsedItems().toString());
        }
}
