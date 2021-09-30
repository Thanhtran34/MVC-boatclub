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
  private ArrayList<Member> thelist = new ArrayList<Member>();

  @Override
  public MemberList checkAllMembers() {
    ObjectMapper mapper = new ObjectMapper();
    MemberList listmember = new MemberList();
    try {
      listmember = mapper.readerFor(MemberList.class).readValue(new File(fileName));
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return listmember;
  }

  @Override
  public void saveMember(Member mb) {
    ObjectMapper mapper = new ObjectMapper();
    try {
      MemberList listmember = new MemberList();
      thelist.add(mb);
      listmember.setMemberList(thelist);
      mapper.writerWithDefaultPrettyPrinter().writeValue(new File(fileName), listmember);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
