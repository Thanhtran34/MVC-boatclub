package controller;

import java.util.ArrayList;
import model.domain.Member;
import model.persistence.Idatastorage;
import view.Iconsole;

/** A class to save and read data. */
public class DataController {

  /**
   * Method to save the data to the file.
   *
   * @param ui {*}
   * @param dataStorage {*}
   * @param members {*}
   */
  public void saveData(Iconsole ui, Idatastorage dataStorage, ArrayList<Member> members) {
    dataStorage.saveMembers(members);
    ui.saveSuccessful();
  }

  /** Method to read data from the file. */
  public void readDataFromFile(Idatastorage dataStorage, ArrayList<Member> members) {
    members = dataStorage.checkAllMembers();
  }
}
