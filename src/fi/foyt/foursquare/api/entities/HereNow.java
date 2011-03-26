/*
 * FoursquareAPI - Foursquare API for Java
 * Copyright (C) 2008 - 2011 Antti Leppä / Foyt
 * http://www.foyt.fi
 * 
 * License: 
 * 
 * Licensed under GNU Lesser General Public License Version 2.1 or later (the "LGPL") 
 * http://www.gnu.org/licenses/lgpl.html
 */

package fi.foyt.foursquare.api.entities;

public class HereNow extends Count {
  
  private static final long serialVersionUID = -39143307292834176L;

  public CheckinGroup[] getGroups() {
    return groups;
  }
  
  private CheckinGroup[] groups;
}
