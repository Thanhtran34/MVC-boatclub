package controller.exception;

/**
 * DuplicationFound exception class.
 * 
 */
public class DuplicationFound extends Exception {
  // Generate error code that identifies the problem.
  private static final long serialVersionUID = -783471368553829068L;
  
  /**
   * Constructor of the class DuplicationFound.
   * 
   */
  public DuplicationFound(String message) {
    super(message);
    System.out.println("======================================");
    System.out.println("Error: " + message);
    System.out.println("======================================");
  }
}
