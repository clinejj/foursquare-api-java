package fi.foyt.foursquare.api.entities.venue;

import fi.foyt.foursquare.api.FoursquareEntity;

public class DeliveryProvider implements FoursquareEntity {

    private static final long serialVersionUID = -6588178110083507195L;

    private String name;

    public String getName() {
        return name;
    }
}
