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

/**
 * Class that represents single notification
 * 
 * @author Antti Leppä / Blake Dy
 *
 * @param <T> type of notification entity
 */
public class Notification<T> {
  
  /**
   * Constructor
   * 
   * @param type type of notification
   * @param item notification entity
   */
  public Notification(NotificationType type, T item) {
    this.type = type;
    this.item = item;
  }
  
  /**
   * Returns notification type
   * 
   * @return notification type
   */
  public NotificationType getType() {
    return type;
  }
  
  /**
   * Returns notification entity
   * 
   * @return notification entity
   */
  public T getItem() {
    return item;
  }
  
  private NotificationType type;
  private T item;
  
  // TODO
  private Boolean alert;
}
