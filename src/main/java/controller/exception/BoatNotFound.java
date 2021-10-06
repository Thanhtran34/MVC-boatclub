package controller.exception;

/**
 * BoatnotFound exception class.
 * 
 */
public class BoatNotFound extends RuntimeException {
  // Generate error code that identifies the problem.
  private static final long serialVersionUID = -843470366553829068L;

  public BoatNotFound(String message) {
    super(message);
    System.out.println("======================================");
    System.out.println("Error: " + message);
    System.out.println("======================================");
  }
}
