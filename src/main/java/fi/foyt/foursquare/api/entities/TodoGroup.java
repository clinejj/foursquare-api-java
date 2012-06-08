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
 * Group of Todos
 * 
 * @author Antti Leppä
 */
public class TodoGroup extends Group<Todo> {

  private static final long serialVersionUID = -8645153668638867533L;

  @Override
  public Todo[] getItems() {
    return items;
  }
  
  private Todo[] items;
}
