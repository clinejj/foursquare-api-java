/*
 * FoursquareAPI - Foursquare API for Java
 * Copyright (C) 2008 - 2011 Antti Leppä / Foyt
 * http://www.foyt.fi
 * Copyright (C) 2014 - Blake Dy / Wallaby
 * http://walla.by
 * 
 * License: 
 * 
 * Licensed under GNU Lesser General Public License Version 3 or later (the "LGPL")
 * http://www.gnu.org/licenses/lgpl.html
 */
package fi.foyt.foursquare.api.entities;

/**
 * Group of UserGroups
 * 
 * @author Antti Leppä / Blake Dy
 */
public class UserGroups extends Count {

  private static final long serialVersionUID = -7444569523527922004L;

  /**
   * Returns user groups
   * 
   * @return user groups
   */
  public UserGroup[] getGroups() {
    return groups;
  }
  
  private UserGroup[] groups;
  
  // TODO
  private String summary;
}
