package de.fh.albsig.hs88455.exceptions;

public class CustomException extends Exception {

  /**
   *
   */
  private static final long serialVersionUID = -2066321249005131954L;

  public CustomException(String msg, Throwable err) {
    super(msg, err);
  }
}
