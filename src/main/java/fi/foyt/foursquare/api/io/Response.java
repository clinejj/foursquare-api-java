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
   * Constructor.
   *
   * @param responseContent response content
   * @param responseCode response code
   * @param message response message
   * @param responseHeaderRateLimit response header X-RateLimit-Limit
   * @param responseHeaderRateLimitRemaining response header X-RateLimit-Remaining
   */
  public Response(String responseContent, int responseCode, String message, String responseHeaderRateLimit, String responseHeaderRateLimitRemaining) {
    this.responseCode = responseCode;
    this.responseContent = responseContent;
    this.message = message;
    this.responseHeaderRateLimit = responseHeaderRateLimit;
    this.responseHeaderRateLimitRemaining = responseHeaderRateLimitRemaining;
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
  
  /**
   * Returns content
   *
   * @return response header rate limit
   */
  public String getResponseHeaderRateLimit() {
      return responseHeaderRateLimit;
  }
  
  /**
   * Returns content
   *
   * @return response header rate limit remaining
   */
  public String getResponseHeaderRateLimitRemaining() {
      return responseHeaderRateLimitRemaining;
  }

  private String responseContent;
  private String message;
  private int responseCode;
  private String responseHeaderRateLimit;
  private String responseHeaderRateLimitRemaining;
  
}
