package controller;

import controller.exception.InvalidInput;
import java.io.IOException;

/** Responsible for staring the application. */
public class App {
  /**
   * Application starting point.
   * 
   */
  public static void main(String[] args) throws InvalidInput, IOException {
    view.Iconsole ui = new view.ConsoleUi();
    controller.ClubController controller = new controller.ClubController();
    controller.runApplication(ui);
  }
}
