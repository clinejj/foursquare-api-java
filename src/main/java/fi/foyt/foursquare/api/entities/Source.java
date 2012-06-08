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
 * Class representing Source entity
 * 
 * @author Antti Leppä
 */
public class Source implements FoursquareEntity {

  private static final long serialVersionUID = -1503607114357562300L;

  /**
   * Returns source's (application's) name
   * 
   * @return source's name
   */
  public String getName() {
    return name;
  }
  
  /**
   * Returns source's (application's) url
   * @return source's url
   */
  public String getUrl() {
    return url;
  }
  
  private String name;
  private String url;
}
