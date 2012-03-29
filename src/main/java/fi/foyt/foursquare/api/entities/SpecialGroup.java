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
 * Group of Specials
 * 
 * @author Antti Leppä
 */
public class SpecialGroup extends Group<CompleteSpecial> {

  private static final long serialVersionUID = 7724731185025537356L;

  @Override
  public CompleteSpecial[] getItems() {
    return items;
  }
  
  private CompleteSpecial[] items;
}
