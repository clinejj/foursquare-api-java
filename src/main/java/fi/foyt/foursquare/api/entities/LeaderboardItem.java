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
 * Class representing LeaderboardItem entity
 * 
 * @author Antti Leppä
 */
public class LeaderboardItem implements FoursquareEntity {

  private static final long serialVersionUID = -7651776711570131592L;

  /**
   * Returns user who's leaderboard item this is
   * 
   * @return user who's leaderboard item this is
   */
  public CompactUser getUser() {
    return user;
  }

  /**
   * Returns rank of user
   * 
   * @return rank of user
   */
  public Integer getRank() {
    return rank;
  }

  /**
   * Returns user's scores
   * 
   * @return user's scores
   */
  public Scores getScores() {
    return scores;
  }

  private CompactUser user;
  private Scores scores;
  private Integer rank;
}