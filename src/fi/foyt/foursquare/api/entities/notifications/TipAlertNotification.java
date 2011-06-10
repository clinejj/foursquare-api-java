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
import fi.foyt.foursquare.api.entities.CompleteTip;

public class TipAlertNotification implements FoursquareEntity {

  private static final long serialVersionUID = -2491430362788377600L;

  public CompleteTip getTip() {
    return tip;
  }

  private CompleteTip tip;
}
