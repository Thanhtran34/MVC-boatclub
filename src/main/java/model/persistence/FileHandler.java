package model.persistence;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import model.domain.Member;

/** Module for the FileHandler class. */
public class FileHandler implements IdataStorage {
  private String fileName = "MemberData.json";

  @Override
  public ArrayList<Member> checkAllMembers() throws JsonProcessingException, IOException {
    ObjectMapper mapper = new ObjectMapper();
    Member[] members = mapper.readValue(Paths.get(fileName).toFile(), Member[].class);
    if (members == null) {
      return new ArrayList<Member>();
    }
    return new ArrayList<>(Arrays.asList(members));
  }

  @Override
  public void saveMembers(ArrayList<Member> members) throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
    writer.writeValue(Paths.get(fileName).toFile(), members);
  }
}
