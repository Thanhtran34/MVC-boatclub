package model.persistence;

import java.util.ArrayList;
import model.domain.Member;

/**
 * An interface for accessing and storing member's data.
 *
 */
public interface Idatastorage {
  public ArrayList<Member> checkAllMembers();

  public void saveMembers(ArrayList<Member> listmember);
}
