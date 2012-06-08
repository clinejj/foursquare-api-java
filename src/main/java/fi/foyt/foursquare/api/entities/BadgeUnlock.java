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

import fi.foyt.foursquare.api.FoursquareEntity;

/**
 * Class representing BadgeUnlock entity
 * 
 * @author Antti Leppä
 */
public class BadgeUnlock implements FoursquareEntity {

  private static final long serialVersionUID = -1266578502619350500L;

  /**
   * Returns array of checkins
   * 
   * @return array of checkins
   */
  public Checkin[] getCheckins() {
    return checkins;
  }

  private Checkin[] checkins;
}
