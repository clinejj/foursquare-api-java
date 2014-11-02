package fi.foyt.foursquare.api.entities;

import fi.foyt.foursquare.api.FoursquareEntity;

public class ScoreGroup implements FoursquareEntity {

    private static final long serialVersionUID = -6364119364984211381L;

    public Integer getTotal() {
        return total;
    }

    public Score[] getScores() {
        return scores;
    }

    private Integer total;
    private Score[] scores;
}
