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

public class CompleteTodos extends Count {

  private static final long serialVersionUID = 2932642396308535360L;

  public Todo[] getItems() {
    return items;
  }
  
  private Todo[] items;
}
