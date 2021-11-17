package model.persistence;

import java.io.IOException;
import java.util.ArrayList;
import model.domain.Member;

/** An interface for accessing and storing member's data. */
public interface IdataStorage {
  ArrayList<Member> readFromFile() throws IOException;

  void saveToFile(ArrayList<Member> members) throws IOException;
}
