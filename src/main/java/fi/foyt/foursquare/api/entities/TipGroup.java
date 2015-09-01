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

import java.util.Arrays;

/**
 * Group of CompactTips
 * 
 * @author Antti Leppä
 */
public class TipGroup extends Group<CompactTip> {

  private static final long serialVersionUID = -9176732625588094423L;

  @Override
  public CompleteTip[] getItems() {
    return items;
  }
  
  private CompleteTip[] items;

  @Override
  public String toString() {
    return "TipGroup{" +
            "items=" + Arrays.toString(items) +
            '}';
  }
}
