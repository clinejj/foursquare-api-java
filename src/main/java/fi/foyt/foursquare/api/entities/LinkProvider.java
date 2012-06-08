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
 * Class representing LinkProvider entity
 * 
 * @author Antti Leppä
 */
public class LinkProvider implements FoursquareEntity {

  private static final long serialVersionUID = -5806669698522916299L;

  /**
   * Returns id of this provider
   * 
   * @return id of this provider
   */
  public String getId() {
    return id;
  }
  
  private String id;
}
