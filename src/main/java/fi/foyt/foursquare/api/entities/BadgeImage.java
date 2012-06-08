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
 * Class representing BadgeImage entity
 * 
 * @author Antti Leppä
 */
public class BadgeImage implements FoursquareEntity {

  private static final long serialVersionUID = 3839275051310645747L;

  /**
   * Returns image prefix
   * 
   * @return image prefix
   */
  public String getPrefix() {
    return prefix;
  }
  
  /**
   * Returns array of image sizes
   * 
   * @return array of image sizes
   */
  public Integer[] getSizes() {
    return sizes;
  }
  
  /**
   * Returns image name
   * 
   * @return image name
   */
  public String getName() {
    return name;
  }
  
  private String prefix;
  private Integer[] sizes;
  private String name;
}
