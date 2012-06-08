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
 * Abstract class representing IOHandler
 * 
 * @author Antti Leppä
 *
 */
public abstract class IOHandler {

  /**
   * Method used in API queries
   * 
   * @param url URL of the query
   * @param method method used
   * @return Response
   */
  public abstract Response fetchData(String url, Method method);
  
  /**
   * Method used in multipart/mime API queries
   * 
   * @param url URL of the query
   * @param params multipart parameters
   * @return Response
   */
  public abstract Response fetchDataMultipartMime(String url, MultipartParameter... params);
}
