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

public class TipGroup extends Group<CompactTip> {

  private static final long serialVersionUID = -9176732625588094423L;

  public CompactTip[] getItems() {
    return items;
  }
  
  private CompactTip[] items;
}
