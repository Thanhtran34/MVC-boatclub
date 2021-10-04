package controller.exception;

/**
 * InvalidInput exception class.
 * 
 */
public class InvalidInput extends Exception {
  // Generate error code that identifies the problem.
  private static final long serialVersionUID = 7718828512143293558L;

  public InvalidInput(String message) {
    super(message);
    System.out.println("Error: " + message);
  }
}
