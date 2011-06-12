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
 * Group of Checkin entities
 * 
 * @author Antti Leppä
 */
public class CheckinGroup extends Group<Checkin> {

  private static final long serialVersionUID = -6971992363107984905L;

  @Override
  public Checkin[] getItems() {
    return items;
  }
  
  private Checkin[] items;
}
