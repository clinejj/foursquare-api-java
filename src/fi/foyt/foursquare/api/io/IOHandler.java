/*
 * FoursquareAPI - Foursquare API for Java
 * Copyright (C) 2008 - 2011 Antti Leppä / Foyt
 * http://www.foyt.fi
 * 
 * License: 
 * 
 * Licensed under GNU Lesser General Public License Version 2.1 or later (the "LGPL") 
 * http://www.gnu.org/licenses/lgpl.html
 */

package fi.foyt.foursquare.api.io;

import fi.foyt.foursquare.api.FoursquareApiException;

public abstract class IOHandler {

  public abstract Response fetchData(String url, Method method) throws FoursquareApiException;
  
}
