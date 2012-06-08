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
 * Class representing Multipart request parameter
 *
 * @author Antti Leppä
 */
public class MultipartParameter {

  /**
   * Constructor
   *
   * @param name name of the parameter
   * @param contentType content type
   * @param content content
   */
  public MultipartParameter(String name, String contentType, byte[] content) {
    this.name = name;
    this.contentType = contentType;
    this.content = content;
  }

  /**
   * Returns content
   *
   * @return content
   */
  public byte[] getContent() {
    return content;
  }
  
  /**
   * Returns content type
   *
   * @return content type
   */
  public String getContentType() {
    return contentType;
  }

  /**
   * Returns parameter name
   * 
   * @return parameter name
   */
  public String getName() {
    return name;
  }

  private String name;

  private String contentType;
  private byte[] content;
}
