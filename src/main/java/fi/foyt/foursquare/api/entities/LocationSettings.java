package fi.foyt.foursquare.api.entities;

import fi.foyt.foursquare.api.FoursquareEntity;

public class LocationSettings implements FoursquareEntity {

    private static final long serialVersionUID = 7618147086632406947L;

    private Boolean showPriceFilter;

    public Boolean getShowPriceFilter() {
        return showPriceFilter;
    }
}
