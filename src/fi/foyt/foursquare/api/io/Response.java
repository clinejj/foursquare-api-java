/*
 * FoursquareAPI - Foursquare API for Java
 * Copyright (C) 2008 - 2011 Antti Leppä / Foyt
 * http://www.foyt.fi
 *
 * License:
 *
 * Licensed under GNU Lesser General Public License Version 3 or later (the "LGPL")
 * http://www.gnu.org/licenses/lgpl.html
 */

package fi.foyt.foursquare.api.io;

/**
 * Class that represents response from IOHandler
 *
 * @author Antti Leppä
 *
 */
public class Response {

  /**
   * Constructor.
   *
   * @param responseContent response content
   * @param responseCode response code
   * @param message response message
   */
  public Response(String responseContent, int responseCode, String message) {
    this.responseCode = responseCode;
    this.responseContent = responseContent;
    this.message = message;
  }

  /**
   * Returns message
   *
   * @return response message
   */
  public String getMessage() {
    return message;
  }

  /**
   * Return code
   *
   * @return response code
   */
  public int getResponseCode() {
    return responseCode;
  }

  /**
   * Returns content
   *
   * @return response content
   */
  public String getResponseContent() {
    return responseContent;
  }

  private String responseContent;
  private String message;
  private int responseCode;
}
