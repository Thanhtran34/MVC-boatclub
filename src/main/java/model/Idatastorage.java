package model;

import java.io.IOException;
import java.util.LinkedList;

/**
 * An interface for storing member's data.
 *
 */
public interface Idatastorage {
  public LinkedList<Member> getMembers() throws IOException;

  public void saveMembers(LinkedList<Member> members) throws IOException;
}
