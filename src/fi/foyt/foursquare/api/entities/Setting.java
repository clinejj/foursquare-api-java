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

public class Setting implements FoursquareEntity {

  private static final long serialVersionUID = -5003261541413796774L;

  public Boolean getReceivePings() {
    return receivePings;
  }
  
  public Boolean getReceiveCommentPings() {
    return receiveCommentPings;
  }
  
  public Boolean getSendToTwitter() {
    return sendToTwitter;
  }
  
  public Boolean getSendMayorshipsToTwitter() {
    return sendMayorshipsToTwitter;
  }
  
  public Boolean getSendBadgesToTwitter() {
    return sendBadgesToTwitter;
  }
  
  public Boolean getSendToFacebook() {
    return sendToFacebook;
  }
  
  public Boolean getSendMayorshipsToFacebook() {
    return sendMayorshipsToFacebook;
  }
  
  public Boolean getSendBadgesToFacebook() {
    return sendBadgesToFacebook;
  }
  
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
