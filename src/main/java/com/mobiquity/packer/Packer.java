package com.mobiquity.packer;

import com.mobiquity.parser.FileParser;
import com.mobiquity.parser.FileReader;
import com.mobiquity.exception.APIException;

import java.io.IOException;
import java.util.List;

public class Packer {

  //private static final Logger logger = LoggerFactory.getLogger(Packer.class);

  private Packer() {
  }

  /**
   * @param filePath
   * @return actual result
   * @throws APIException
   */
  public static String pack(String filePath) throws APIException {
    if(filePath.isEmpty()||filePath==null){
      throw new APIException("Invalid file or file path");
    }
    List<String> textFileLines;
try {
  textFileLines = FileReader.readFile(filePath);
  return FileParser.ProcessFile(textFileLines);
}
catch (Exception e){
  throw new APIException("Invalid file path");
}
  }




}
