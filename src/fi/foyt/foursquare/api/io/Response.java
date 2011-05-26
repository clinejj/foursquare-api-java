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

public class Response {

  public Response(String responseContent, int responseCode, String message) {
    this.responseCode = responseCode;
    this.responseContent = responseContent;
    this.message = message;
  }
  
  public String getMessage() {
    return message;
  }
  
  public int getResponseCode() {
    return responseCode;
  }
  
  public String getResponseContent() {
    return responseContent;
  }
  
  private String responseContent;
  private String message;
  private int responseCode;
}
