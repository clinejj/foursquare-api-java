/*
 * FoursquareAPI - Foursquare API for Java
 * Copyright (C) 2008 - 2011 Antti Leppä / Foyt
 * http://www.foyt.fi
 * Copyright (C) 2014 - Blake Dy / Wallaby
 * http://walla.by
 * 
 * License: 
 * 
 * Licensed under GNU Lesser General Public License Version 3 or later (the "LGPL")
 * http://www.gnu.org/licenses/lgpl.html
 */

package fi.foyt.foursquare.api.entities.notifications;

import fi.foyt.foursquare.api.FoursquareEntity;

/**
 * Entity representing "Message" notification
 * 
 * @author Antti Leppä / Blake Dy
 */
public class MessageNotification implements FoursquareEntity {

  private static final long serialVersionUID = 1341444447020549517L;

  /**
   * Returns message
   * 
   * @return message
   */
  public String getMessage() {
    return message;
  }

  private String message;
  
  // TODO
  private String entities;
}
