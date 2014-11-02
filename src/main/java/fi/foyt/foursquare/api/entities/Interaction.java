package fi.foyt.foursquare.api.entities;

import fi.foyt.foursquare.api.FoursquareEntity;

public class Interaction implements FoursquareEntity {

    private static final long serialVersionUID = -7405129004437609517L;

    private String entryUrl;

    public String getEntryUrl() {
        return entryUrl;
    }
}
