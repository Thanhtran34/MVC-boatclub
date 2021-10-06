package controller;

import java.io.IOException;
import java.util.ArrayList;
import model.domain.Member;
import model.persistence.FileHandler;
import model.persistence.Idatastorage;
import view.Iconsole;

/** A class to save and read data. */
public class DataController {
  public Idatastorage dataStorage;

  public DataController() {
    this.dataStorage = new FileHandler();
  }

  /**
   * Method to save the data to the file.
   *
   * @param ui {*}
   * @param members {*}
   */
  public void saveData(Iconsole ui, ArrayList<Member> members) throws IOException {
    this.dataStorage.saveMembers(members);
    ui.saveSuccessful();
  }

  /** Method to read data from the file. */
  public ArrayList<Member> readDataFromFile() throws IOException {
    return this.dataStorage.checkAllMembers();
  }
}
