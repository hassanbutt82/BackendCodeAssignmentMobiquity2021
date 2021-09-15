package com.mobiquity.packer;

import com.mobiquity.exception.APIException;
import com.mobiquity.knapsack.IKnapsackSolution;
import com.mobiquity.knapsack.impl.KnapsackSolutionImpl;
import com.mobiquity.model.ParsedFileResult;
import com.mobiquity.parser.FileParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PackerTest {
    private static final String NO_RESULT_TEST = "8 : (1,15.3,€34)";
    private IKnapsackSolution knapsackSolutionImpl = new KnapsackSolutionImpl();

    @Test
    public void packerTest() throws APIException, URISyntaxException, IOException {
        URI resourceInputFile = com.mobiquity.packer.PackerTest.class.getClassLoader().getSystemResource("example_input").toURI();
        final String actualOutput  = Packer.pack(Paths.get(Paths.get(resourceInputFile).toString()).toString());
        final String expectedOutput = "4\n" + "-\n" + "2,7\n" + "8,9";
        Assertions.assertEquals(expectedOutput,actualOutput);
    }
    @Test
    public void packPackageNoResultTest() throws Exception {
        final ParsedFileResult parsedFileResult= FileParser.fileParsing(NO_RESULT_TEST);
        String result = knapsackSolutionImpl.findCombination(parsedFileResult.getPackageCapacity(),parsedFileResult.getParsedItems());

        // empty output expected
        Assertions.assertEquals("-", result);
    }
}
