package controller;

import java.util.ArrayList;
import controller.exception.BoatNotFound;
import controller.exception.MemberNotFound;
import model.domain.Boat;
import model.domain.Member;
import view.Iconsole;

/** Interface to group all methods for boat controller. */
public interface IboatController {
  void registerBoat(Iconsole ui, ArrayList<Member> members) throws MemberNotFound;

  void updateBoat(Iconsole ui, ArrayList<Member> members) throws MemberNotFound, BoatNotFound;

  void deleteBoat(Iconsole ui, ArrayList<Member> members) throws MemberNotFound, BoatNotFound;

  Boat.BoatType getBoatTypes(Iconsole ui);

  int getBoatId(Iconsole ui, Member member, ArrayList<Member> members);
}
