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
import fi.foyt.foursquare.api.entities.LeaderboardScore;

/**
 * Entity representing "Score" notification
 * 
 * @author Antti Leppä
 */
public class ScoreNotification implements FoursquareEntity {

  private static final long serialVersionUID = -5334783390151137440L;

  /**
   * Returns array of LeaderboardScore entities
   * 
   * @return array of LeaderboardScore entities
   */
  public LeaderboardScore[] getScores() {
    return scores;
  }
  
  /**
   * Returns total points
   * 
   * @return total points
   */
  public Long getTotal() {
    return total;
  }
  
  private LeaderboardScore[] scores;
  private Long total;
}
