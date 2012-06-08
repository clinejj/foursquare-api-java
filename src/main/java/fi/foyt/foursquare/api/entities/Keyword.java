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
 * Class representing Keyword entity
 * 
 * @author Antti Leppä
 */
public class Keyword implements FoursquareEntity {

  private static final long serialVersionUID = -3697725825647022962L;

  /**
   * Returns display name
   * 
   * @return display name
   */
  public String getDisplayName() {
    return displayName;
  }
  
  /**
   * Returns keyword
   * 
   * @return keyword
   */
  public String getKeyword() {
    return keyword;
  }
  
  private String displayName;
  private String keyword;
}