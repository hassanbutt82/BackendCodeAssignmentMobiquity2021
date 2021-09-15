package com.mobiquity.parser;

import com.mobiquity.exception.APIException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileReader {


    /**
     * @param path path of file to parse and find combination from
     * @return
     * @throws IOException
     * @throws APIException
     */
    public static List<String> readFile(String path) throws APIException {

        if(path==null || path.isEmpty()){
            throw new APIException("File is not valid");
        }
        List<String> textFileLines;
        try {
            textFileLines = Files.readAllLines(Paths.get(path));
            return textFileLines;
        }

        catch (IOException ioException){
            throw new APIException("Error while file reading");
        }

    }


}
