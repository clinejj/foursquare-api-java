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

public class FriendGroup extends Group<CompactUser> {

  private static final long serialVersionUID = -8661984374396068690L;

  public String getType() {
    return type;
  }
  
  @Override
  public CompactUser[] getItems() {
    return items;
  }
  
  private String type;
  private CompactUser[] items;
}
