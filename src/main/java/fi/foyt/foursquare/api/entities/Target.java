package fi.foyt.foursquare.api.entities;

import fi.foyt.foursquare.api.FoursquareEntity;

public class Target implements FoursquareEntity {

    private static final long serialVersionUID = -3384967095660315321L;

    public String getType() {
        return type;
    }

    public TargetObject getObject() {
        return object;
    }

    private String type;
    private TargetObject object;
}
