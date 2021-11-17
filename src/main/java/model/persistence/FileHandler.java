package model.persistence;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import model.domain.Member;

/** The FileHandler class to read and save data to file. */
public class FileHandler implements IdataStorage {
  private String fileName = "MemberData.json";

  @Override
  public ArrayList<Member> readFromFile() throws JsonProcessingException, IOException {
    ObjectMapper mapper = new ObjectMapper();
    Member[] members = mapper.readValue(Paths.get(fileName).toFile(), Member[].class);
    if (members == null) {
      return new ArrayList<Member>();
    }
    return new ArrayList<>(Arrays.asList(members));
  }

  @Override
  public void saveToFile(ArrayList<Member> members) throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
    writer.writeValue(Paths.get(fileName).toFile(), members);
  }
}
