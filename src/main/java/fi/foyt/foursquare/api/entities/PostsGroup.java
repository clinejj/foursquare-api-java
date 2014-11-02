package fi.foyt.foursquare.api.entities;

import fi.foyt.foursquare.api.FoursquareEntity;

public class PostsGroup implements FoursquareEntity {

    // TODO: Fill out this class (have not seen full response with it)

    private static final long serialVersionUID = -8278706933480753763L;

    public Integer getCount() {
        return count;
    }

    public Integer getTextCount() {
        return textCount;
    }

    private Integer count;
    private Integer textCount;
}
