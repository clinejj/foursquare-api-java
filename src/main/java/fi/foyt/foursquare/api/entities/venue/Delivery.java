package fi.foyt.foursquare.api.entities.venue;

import fi.foyt.foursquare.api.FoursquareEntity;

public class Delivery implements FoursquareEntity {

    private static final long serialVersionUID = 8432510318771496631L;

    private String url;
    private DeliveryProvider provider;

    public String getUrl() {
        return url;
    }

    public DeliveryProvider getProvider() {
        return provider;
    }
}
