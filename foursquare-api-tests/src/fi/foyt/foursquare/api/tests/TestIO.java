package fi.foyt.foursquare.api.tests;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import fi.foyt.foursquare.api.io.IOHandler;
import fi.foyt.foursquare.api.io.Method;
import fi.foyt.foursquare.api.io.Response;

public class TestIO extends IOHandler {

  @Override
  public Response fetchData(String url, Method method) throws IOException {
    StringBuilder searchUrlParametersBuilder = new StringBuilder(); 
    
    boolean callback = false;
    
    int queryStart = url.indexOf("?");
    String searchUrl = url.substring(0, queryStart);
    String query = url.substring(queryStart + 1);
    Iterator<String> parameters = Arrays.asList(query.split("&")).iterator();
    
    while (parameters.hasNext()) {
      String[] p = parameters.next().split("=");
      
      boolean authToken = "oauth_token".equals(p[0]);
      
      if (authToken) {
        if ("null".equals(p[1]))
          return new Response("", 401, "Unauthorized");
      }

      boolean clientParam = "client_id".equals(p[0]) || "client_secret".equals(p[0]);
      boolean versionParam = "v".equals(p[0]);      
      boolean callbackParam = "callback".equals(p[0]);
      
      if (!clientParam && !authToken && !versionParam && !callbackParam) {
        if (searchUrlParametersBuilder.length() > 0)
          searchUrlParametersBuilder.append('&');
        searchUrlParametersBuilder.append(p[0] + "=" + p[1]);
      } 
      
      if (callbackParam)
        callback = true;
    }
    
    String searchUrlParameters = searchUrlParametersBuilder.toString();
    if (searchUrlParameters.length() > 0) {
      searchUrl += '?' + searchUrlParameters;
    }
    
    String path = response.get(searchUrl);
    if (path != null) {
      StringWriter responseWriter = new StringWriter();
      
      if (callback)
        responseWriter.append("c(");
      
      char[] buf = new char[1024];
      int l = 0;
      
      File file = new File("data/" + path);
      FileReader fileReader = new FileReader(file);
      while ((l = fileReader.read(buf)) > 0) {
        responseWriter.write(buf, 0, l);
      }

      responseWriter.flush();
      responseWriter.close();      
      
      if (callback)
        responseWriter.append(");");
      
      return new Response(responseWriter.getBuffer().toString(), 200, "");
    } else {
      return new Response("", 404, url + " - Not found");
    }
  }

  private static void setResponse(String url, String responsePath) {
    response.put(url, responsePath);
  } 
  
  private static Map<String, String> response = new HashMap<String, String>();
  
  static {
    setResponse("https://foursquare.com/oauth2/access_token?grant_type=authorization_code&redirect_uri=FAKE_REDIRECT_URL&code=FAKE_CODE", "auth/token_1.json");
    setResponse("https://api.foursquare.com/v2/specials/4da37ddb15ad530c110a9d52?venueId=4cb38bf20cdc721ea943234f", "specials/id_1.json");
    setResponse("https://api.foursquare.com/v2/specials/search?ll=40.7%2C-73.9", "specials/search_1.json");
    setResponse("https://api.foursquare.com/v2/settings/all", "settings/all_1.json");
    setResponse("https://api.foursquare.com/v2/settings/receivePings/set?value=0", "settings/set_1.json");
    setResponse("https://api.foursquare.com/v2/checkins/4d627f6814963704dc28ff94?signature=LPtzP4edmpbaspdKhI9-892UoFM", "checkins/id_1.json");
    setResponse("https://api.foursquare.com/v2/checkins/4d7b44d7f260a0932e5024ba", "checkins/id_2.json");
    setResponse("https://api.foursquare.com/v2/checkins/4de4762d52b1d38d299e6000", "checkins/id_3.json");
    setResponse("https://api.foursquare.com/v2/checkins/add?venueId=4c6bbfafa48420a1b09a0a0b&broadcast=private&ll=61.68777583849969%2C27.273173332214355", "checkins/add_1.json");
    setResponse("https://api.foursquare.com/v2/checkins/add?venue=Test&broadcast=public&ll=40%2C40", "checkins/add_2.json");
    setResponse("https://api.foursquare.com/v2/checkins/add?venueId=408c5100f964a520c6f21ee3&broadcast=public", "checkins/add_3.json");
    setResponse("https://api.foursquare.com/v2/checkins/recent", "checkins/recent_1.json");
    setResponse("https://api.foursquare.com/v2/users/self", "users/id_1.json");
    setResponse("https://api.foursquare.com/v2/users/1504602", "users/id_2.json");
    setResponse("https://api.foursquare.com/v2/users/7613255/request", "users/request_1.json");
    setResponse("https://api.foursquare.com/v2/users/7613255/unfriend", "users/unfriend_1.json");
    setResponse("https://api.foursquare.com/v2/users/10078668/approve", "users/approve_1.json");
    setResponse("https://api.foursquare.com/v2/users/10078668/deny", "users/deny_1.json");
    setResponse("https://api.foursquare.com/v2/users/self/checkins", "users/checkins_1.json");
    setResponse("https://api.foursquare.com/v2/users/self/friends", "users/friends_1.json");
    setResponse("https://api.foursquare.com/v2/users/search?twitter=naveen", "users/search_1.json");
    setResponse("https://api.foursquare.com/v2/users/requests", "users/requests_1.json");
    setResponse("https://api.foursquare.com/v2/venues/5104", "venues/id_1.json");
    setResponse("https://api.foursquare.com/v2/venues/categories", "venues/categories_1.json");
    setResponse("https://api.foursquare.com/v2/venues/search?ll=40.7%2C-74", "venues/search_1.json");
    setResponse("https://api.foursquare.com/v2/venues/trending?ll=40.7%2C-74", "venues/trending_1.json");
    setResponse("https://api.foursquare.com/v2/venues/add?name=Apuv%C3%A4lineyksikk%C3%B6+%2F+Moision+toimipiste&address=Moisiontie+11+b&city=Mikkeli&state=Etel%C3%A4-Savo&zip=50520&phone=0443516511&ll=61.677701%2C27.272585&primaryCategoryId=4bf58dd8d48988d104941735", "venues/add_1.json");
  }
}