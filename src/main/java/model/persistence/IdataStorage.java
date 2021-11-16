package model.persistence;

import java.io.IOException;
import java.util.ArrayList;
import model.domain.Member;

/** An interface for accessing and storing member's data. */
public interface IdataStorage {
  public ArrayList<Member> readFromFile() throws IOException;

  public void saveToFile(ArrayList<Member> members) throws IOException;
}
