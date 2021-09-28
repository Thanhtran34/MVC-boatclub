package model;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.LinkedList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

public class FileHandler implements Idatastorage {
  @Override
  public LinkedList<Member> getMembers() throws IOException {
    Writer writer = new FileWriter("MemberData.json");
    Gson gson = new GsonBuilder().create();
    return null;
  }

  @Override
  public void saveMembers(LinkedList<Member> members) throws IOException {}
}
