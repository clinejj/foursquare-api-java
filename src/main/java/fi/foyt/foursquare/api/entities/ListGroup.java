/*
 * FoursquareAPI - Foursquare API for Java
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
 * Group of List entities
 * 
 * @author Blake Dy
 */
public class ListGroup extends Group<List> {

  private static final long serialVersionUID = -8365419856910618259L;

  @Override
  public List[] getItems() {
    return items;
  }

  private List[] items;
}
