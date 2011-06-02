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
import fi.foyt.foursquare.api.entities.LeaderboardItem;
import fi.foyt.foursquare.api.entities.LeaderboardScore;

public class LeaderboardNotification implements FoursquareEntity {

  private static final long serialVersionUID = 8919261709842197077L;

  public LeaderboardItem[] getLeaderboard() {
    return leaderboard;
  }
  
  public String getMessage() {
    return message;
  }

  public LeaderboardScore[] getScores() {
    return scores;
  }
  
  public Long getTotal() {
    return total;
  }
  
  private LeaderboardItem[] leaderboard;
  private String message;
  private LeaderboardScore[] scores;
  private Long total;
} 