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
 * Group of Keyword entities
 * 
 * @author Antti Leppä
 */
public class KeywordGroup extends Group<Keyword> {

  private static final long serialVersionUID = 8269600369584702559L;

  @Override
  public Keyword[] getItems() {
    return items;
  }
  
  private Keyword[] items;
}
