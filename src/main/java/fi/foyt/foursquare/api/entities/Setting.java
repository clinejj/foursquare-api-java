/*
 * FoursquareAPI - Foursquare API for Java
 * Copyright (C) 2008 - 2011 Antti Leppä / Foyt
 * http://www.foyt.fi
 *
 * License:
 *
 * Licensed under GNU Lesser General Public License Version 3 or later (the "LGPL")
 * http://www.gnu.org/licenses/lgpl.html
 */

package fi.foyt.foursquare.api.entities;

import fi.foyt.foursquare.api.FoursquareEntity;

/**
 * Class representing Setting entity
 *
 * @author Antti Leppä
 */
public class Setting implements FoursquareEntity {

    private static final long serialVersionUID = -5003261541413796774L;

    /**
     * Returns true of false indicating if user will receive pings.
     *
     * @return true of false indicating if user will receive pings.
     */
    public Boolean getReceivePings() {
        return receivePings;
    }

    /**
     * Returns true of false indicating if user's data will be sent to Twitter
     *
     * @return true of false indicating if user's data will be sent to Twitter
     */
    public Boolean getSendToTwitter() {
        return sendToTwitter;
    }

    /**
     * Returns true of false indicating if user's data will be sent to Facebook
     *
     * @return true of false indicating if user's data will be sent to Facebook
     */
    public Boolean getSendToFacebook() {
        return sendToFacebook;
    }

    /**
     * Returns foreign consent
     *
     * @return foreign consent
     */
    public String getForeignConsent() {
        return foreignConsent;
    }

    public String getTwitter() {
        return twitter;
    }

    public Boolean getTwitterLinked() {
        return twitterLinked;
    }

    public Boolean getSendTipsToTwitter() {
        return sendTipsToTwitter;
    }

    public Long getFacebook() {
        return facebook;
    }

    public String[] getFacebookPermissions() {
        return facebookPermissions;
    }

    public Boolean getFacebookLinked() {
        return facebookLinked;
    }

    public Boolean getFacebookFrictionless() {
        return facebookFrictionless;
    }

    public Boolean getFacebookTimeline() {
        return facebookTimeline;
    }

    public Boolean getPasswordSet() {
        return passwordSet;
    }

    public Boolean getSendTipsToFacebook() {
        return sendTipsToFacebook;
    }

    public Boolean getPageUpdates() {
        return pageUpdates;
    }

    public Boolean getCanBeCheckedInByFriends() {
        return canBeCheckedInByFriends;
    }

    public String getMapKey() {
        return mapKey;
    }

    public LocationSettings getLocationSettings() {
        return locationSettings;
    }

    public Boolean getEligibleForRadarSplash() {
        return eligibleForRadarSplash;
    }

    public Boolean getAlreadySeenRadarSplash() {
        return alreadySeenRadarSplash;
    }

    public String getMeasurementSystem() {
        return measurementSystem;
    }

    public Boolean getAllowSharesFromFollowers() {
        return allowSharesFromFollowers;
    }

    private Boolean receivePings;
    private Boolean sendToTwitter;
    private Boolean sendToFacebook;
    private String foreignConsent;
    private String twitter;
    private Boolean twitterLinked;
    private Boolean sendTipsToTwitter;
    private Long facebook;
    private String[] facebookPermissions;
    private Boolean facebookLinked;
    private Boolean facebookFrictionless;
    private Boolean facebookTimeline;
    private Boolean passwordSet;
    private Boolean sendTipsToFacebook;
    private Boolean pageUpdates;
    private Boolean canBeCheckedInByFriends;
    private String mapKey;
    private LocationSettings locationSettings;
    private Boolean eligibleForRadarSplash;
    private Boolean alreadySeenRadarSplash;
    private String measurementSystem;
    private Boolean allowSharesFromFollowers;

}
