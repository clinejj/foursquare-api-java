package fi.foyt.foursquare.api.entities.venue;

import fi.foyt.foursquare.api.FoursquareEntity;

public class Reservations implements FoursquareEntity {

    private static final long serialVersionUID = -1058503314777039722L;

    private String url;

    public String getUrl() {
        return url;
    }
}
