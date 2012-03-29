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

package fi.foyt.foursquare.api.entities;

import fi.foyt.foursquare.api.FoursquareEntity;

/**
 * Class representing Size entity
 * 
 * @author Antti Leppä
 */
public class Size implements FoursquareEntity {

  private static final long serialVersionUID = 4506057169243630084L;

  /**
   * Returns height
   * 
   * @return height
   */
  public Integer getHeight() {
    return height;
  }
  
  /**
   * Returns URL to image
   * 
   * @return URL to image
   */
  public String getUrl() {
    return url;
  }
  
  /**
   * Returns width
   * 
   * @return width
   */
  public Integer getWidth() {
    return width;
  }
  
  private String url;
  private Integer width;
  private Integer height;
}
