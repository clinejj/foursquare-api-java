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
 * Class representing Setting entity
 * 
 * @author Antti Leppä
 */
public class Setting implements FoursquareEntity {

  private static final long serialVersionUID = -5003261541413796774L;

  /**
   * Returns true of false indicating if user will receive pings.
   * 
   * @return true of false indicating if user will receive pings.
   */
  public Boolean getReceivePings() {
    return receivePings;
  }
  
  /**
   * Returns true of false indicating if user will receive comment pings.
   * 
   * @return true of false indicating if user will receive comment pings.
   */
  public Boolean getReceiveCommentPings() {
    return receiveCommentPings;
  }
  
  /**
   * Returns true of false indicating if user's data will be sent to Twitter
   * 
   * @return true of false indicating if user's data will be sent to Twitter
   */
  public Boolean getSendToTwitter() {
    return sendToTwitter;
  }
  
  /**
   * Returns true of false indicating if user's mayorships will be sent to Twitter
   *  
   * @return true of false indicating if user's mayorships will be sent to Twitter
   */
  public Boolean getSendMayorshipsToTwitter() {
    return sendMayorshipsToTwitter;
  }
  
  /**
   * Returns true of false indicating if user's badges will be sent to Twitter
   *  
   * @return true of false indicating if user's badges will be sent to Twitter
   */
  public Boolean getSendBadgesToTwitter() {
    return sendBadgesToTwitter;
  }
  
  /**
   * Returns true of false indicating if user's data will be sent to Facebook
   *  
   * @return true of false indicating if user's data will be sent to Facebook
   */
  public Boolean getSendToFacebook() {
    return sendToFacebook;
  }
  
  /**
   * Returns true of false indicating if user's mayorships will be sent to Facebook
   * 
   * @return true of false indicating if user's mayorships will be sent to Facebook
   */
  public Boolean getSendMayorshipsToFacebook() {
    return sendMayorshipsToFacebook;
  }
  
  /**
   * Returns true of false indicating if user's badges will be sent to Facebook
   * 
   * @return true of false indicating if user's badges will be sent to Facebook
   */
  public Boolean getSendBadgesToFacebook() {
    return sendBadgesToFacebook;
  }
  
  /**
   * Returns foreign consent 
   * 
   * @return foreign consent 
   */
  public String getForeignConsent() {
    return foreignConsent;
  }
  
  private Boolean receivePings;
  private Boolean receiveCommentPings;
  private Boolean sendToTwitter;
  private Boolean sendMayorshipsToTwitter;
  private Boolean sendBadgesToTwitter;
  private Boolean sendToFacebook;
  private Boolean sendMayorshipsToFacebook;
  private Boolean sendBadgesToFacebook;
  private String foreignConsent;
}
