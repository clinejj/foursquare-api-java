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

package fi.foyt.foursquare.api;

/**
 * FoursquareAPI exception
 * 
 * @author Antti Leppä
 */
public class FoursquareApiException extends Exception {

  private static final long serialVersionUID = -4581357612541474483L;

  /**
   * Constructor
   * 
   * @param message message
   */
  public FoursquareApiException(String message) {
    super(message);
  }
  
  /**
   * Constructor
   * 
   * @param t throwable
   */
  public FoursquareApiException(Throwable t) {
    super(t);
  }
}
