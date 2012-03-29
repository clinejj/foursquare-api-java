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
 * Group of Venues
 * 
 * @author Antti Leppä
 */
public class VenueGroup extends Group<CompactVenue> {

  private static final long serialVersionUID = -996401659508844800L;

  @Override
  public CompactVenue[] getItems() {
    return items;
  }
  
  private CompactVenue[] items;
}
