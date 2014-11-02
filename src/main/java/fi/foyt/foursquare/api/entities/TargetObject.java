package fi.foyt.foursquare.api.entities;

import fi.foyt.foursquare.api.FoursquareEntity;

public class TargetObject implements FoursquareEntity {

    private static final long serialVersionUID = -138040647032538603L;

    public String getUrl() {
        return url;
    }

    private String url;
}
