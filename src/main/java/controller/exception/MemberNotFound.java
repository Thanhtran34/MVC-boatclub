package controller.exception;

/**
 * MemberNotFound exception class.
 */
public class MemberNotFound extends RuntimeException {
  // Generate error code that identifies the problem.
  private static final long serialVersionUID = -8460356990632230194L;

  public MemberNotFound(String message) {
    super(message);
    System.out.println("Error: " + message);
  }
}
