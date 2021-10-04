package model.persistence;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import model.domain.Member;

/** Module for the FileHandler class. */
public class FileHandler implements Idatastorage {
  private String fileName = "MemberData.json";
  private MemberList listmember = new MemberList();

  @Override
  public ArrayList<Member> checkAllMembers() {
    ObjectMapper mapper = new ObjectMapper();
    ArrayList<Member> members = new ArrayList<>();
    try {
      members = mapper.readerFor(MemberList.class).readValue(new File(fileName));
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return members;
  }

  @Override
  public void saveMembers(ArrayList<Member> thelist) {
    ObjectMapper mapper = new ObjectMapper();
    try {
      listmember.setMemberList(thelist);
      mapper.writerWithDefaultPrettyPrinter().writeValue(new File(fileName), listmember);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
