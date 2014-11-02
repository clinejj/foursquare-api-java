package fi.foyt.foursquare.api.entities;

import fi.foyt.foursquare.api.FoursquareEntity;

public class Score implements FoursquareEntity {

    private static final long serialVersionUID = -7178584342940281071L;

    public String getIcon() {
        return icon;
    }

    public String getMessage() {
        return message;
    }

    public Integer getPoints() {
        return points;
    }

    public Target getTarget() {
        return target;
    }

    private String icon;
    private String message;
    private Integer points;
    private Target target;
}
