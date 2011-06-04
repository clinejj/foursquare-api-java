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

package fi.foyt.foursquare.api.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DefaultIOHandler extends IOHandler {

  @Override
  public Response fetchData(String url, Method method) {
    int code = 200;
    
    try {
      URL aUrl = new URL(url);
      HttpURLConnection connection = (HttpURLConnection) aUrl.openConnection();
      try {
        connection.setDoInput(true);
        connection.setDoOutput(true);
        connection.setRequestMethod(method.name());
        connection.connect();
        StringWriter responseWriter = new StringWriter();

        code = connection.getResponseCode();
        if (code == 200) {
          InputStream inputStream = connection.getInputStream();
          char[] buf = new char[1024];
          int l = 0;
  
          InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
          while ((l = inputStreamReader.read(buf)) > 0) {
            responseWriter.write(buf, 0, l);
          }
  
          responseWriter.flush();
          responseWriter.close();
  
          int responseCode = connection.getResponseCode();
          String responseContent = responseWriter.getBuffer().toString();
          
          System.out.println(responseContent);
          
          return new Response(responseContent, responseCode, connection.getResponseMessage());
        } else {

          String message = "";
          switch (code) {
            case 400:
              message = "Bad Request";
            break;
            case 401:
              message = "Unauthorized";
            break;
            case 403:
              message = "Forbidden";
            break;
            case 404:
              message = "Not Found";
            break;
            case 405:
              message = "Method Not Allowed";
            break;
            case 500:
              message = "Internal Server Error";
            break;
          }
          
          return new Response("", code, message);
        } 

      } finally {
        connection.disconnect();
      }
    } catch (MalformedURLException e) {
      return new Response("", 400, "Malformed URL: " + url);
    } catch (IOException e) {
      return new Response("", 500, e.getMessage());
    } 
  }
}
