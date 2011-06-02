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

package fi.foyt.foursquare.api.entities.notifications;

import fi.foyt.foursquare.api.FoursquareEntity;
import fi.foyt.foursquare.api.entities.CompactTip;

public class TipNotification implements FoursquareEntity {

  private static final long serialVersionUID = -4625637368235280260L;

  public String getName() {
    return name;
  }
  
  public CompactTip getTip() {
    return tip;
  }

  private String name;
  private CompactTip tip;
}
