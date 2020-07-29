package de.fh.albsig.hs88455.exceptions;

public class CustomOpenWeatherException extends Exception {

  /**
   * CustomException class.
   */
  private static final long serialVersionUID = -2066321249005131954L;

  public CustomOpenWeatherException(String msg, Throwable err) {
    super(msg, err);
  }
}
