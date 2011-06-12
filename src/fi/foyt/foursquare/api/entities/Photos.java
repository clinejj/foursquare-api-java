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

/**
 * Group of PhotoGroups
 * 
 * @author Antti Leppä
 */
public class Photos extends Count {
  
  private static final long serialVersionUID = 1974338391344968466L;

  /**
   * Returns array of PhotoGroups
   *  
   * @return array of PhotoGroups
   */
  public PhotoGroup[] getGroups() {
    return groups;
  }
  
  private PhotoGroup[] groups;
}
