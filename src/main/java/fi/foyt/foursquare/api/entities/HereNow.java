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
 * Class representing HereNow entity
 * 
 * @author Antti Leppä
 */
public class HereNow extends Count {
  
  private static final long serialVersionUID = -39143307292834176L;

  /**
   * Returns array of checkin groups
   * 
   * @return array of checkin groups
   */
  public CheckinGroup[] getGroups() {
    return groups;
  }
  
  private CheckinGroup[] groups;
}
