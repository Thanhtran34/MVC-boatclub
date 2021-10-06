package model.persistence;

import java.io.IOException;
import java.util.ArrayList;
import model.domain.Member;

/**
 * An interface for accessing and storing member's data.
 *
 */
public interface Idatastorage {
  public ArrayList<Member> checkAllMembers() throws IOException;

  public void saveMembers(ArrayList<Member> listmember) throws IOException;
}
