package fi.foyt.foursquare.api.entities.notifications;

import fi.foyt.foursquare.api.FoursquareEntity;
import fi.foyt.foursquare.api.entities.LeaderboardScore;

public class ScoreNotification implements FoursquareEntity {

  private static final long serialVersionUID = -5334783390151137440L;

  public LeaderboardScore[] getScores() {
    return scores;
  }
  
  public Long getTotal() {
    return total;
  }
  
  private LeaderboardScore[] scores;
  private Long total;
}
