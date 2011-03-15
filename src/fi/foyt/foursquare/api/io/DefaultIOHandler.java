/*
 * FoursquareAPI - Foursquare API for Java
 * Copyright (C) 2008 - 2011 Antti LeppŠ / Foyt
 * http://www.foyt.fi
 * 
 * License: 
 * 
 * Licensed under GNU Lesser General Public License Version 2.1 or later (the "LGPL") 
 * http://www.gnu.org/licenses/lgpl.html
 */

package fi.foyt.foursquare.api.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import fi.foyt.foursquare.api.FoursquareApiException;

public class DefaultIOHandler extends IOHandler {

  @Override
  public Response fetchData(String url, Method method) throws FoursquareApiException {
    try {
      URL aUrl = new URL(url);
      HttpURLConnection connection = (HttpURLConnection) aUrl.openConnection();
      connection.setDoInput(true);
      connection.setDoOutput(true);
      connection.setRequestMethod(method.name());
      connection.connect();

      StringWriter responseWriter = new StringWriter();

      InputStream inputStream = connection.getInputStream();
      char[] buf = new char[1024];
      int l = 0;

      InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
      while ((l = inputStreamReader.read(buf)) > 0) {
        responseWriter.write(buf, 0, l);
      }

      responseWriter.flush();
      responseWriter.close();

      int responseCode = connection.getResponseCode();
      String responseContent = responseWriter.getBuffer().toString();

      return new Response(responseContent, responseCode, connection.getResponseMessage());

    } catch (MalformedURLException e) {
      throw new FoursquareApiException(e);
    } catch (IOException e) {
      throw new FoursquareApiException(e);
    }
  }
}
