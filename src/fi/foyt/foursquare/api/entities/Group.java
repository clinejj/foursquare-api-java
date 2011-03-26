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

public abstract class Group<T> extends Count {

  private static final long serialVersionUID = -3156890964170514232L;

  public String getType() {
    return type;
  }

  public String getName() {
    return name;
  }
  
  public abstract T[] getItems();

  private String type;
  private String name;
}
