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
 * Group of LeaderboardItems
 * 
 * @author Antti Leppä
 */
public class LeaderboardItemGroup extends Group<LeaderboardItem> {

  private static final long serialVersionUID = 4074401404859223744L;

  @Override
  public LeaderboardItem[] getItems() {
    return items;
  }
  
  private LeaderboardItem[] items;
}
