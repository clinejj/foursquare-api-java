package fi.foyt.foursquare.api.tests;

import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import fi.foyt.foursquare.api.io.IOHandler;
import fi.foyt.foursquare.api.io.Method;
import fi.foyt.foursquare.api.io.MultipartParameter;
import fi.foyt.foursquare.api.io.Response;

public class TestIO extends IOHandler {

  @Override
  public Response fetchData(String url, Method method) {
    try {
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
        
        // use inputstream reader instead because we are loading from classpath now 
       // File file = new File("data/" + path);
      //  FileReader fileReader = new FileReader(file);
        InputStream in = this.getClass().getResourceAsStream("/data/" + path);
        InputStreamReader reader = new InputStreamReader(in);
        while ((l = reader.read(buf)) > 0) {
          responseWriter.write(buf, 0, l);
        }
  
        responseWriter.flush();
        responseWriter.close();      
        
        if (callback)
          responseWriter.append(");");
        
        return new Response(responseWriter.getBuffer().toString(), 200, "");
      } else {
        if (callback) {
          return new Response(
              "c({meta: { " +
              "  code: 404, " +
              "  errorType: \"endpoint_error\", " +
              "  errorDetail: \"The requested path does not exist.\" " +
              "}, response: { }});", 200, "");
        } else {
          return new Response("", 404, "Not Found");
        }
      }
    } catch (Exception e) {
      return new Response("", 500, e.getMessage());
    }
  }
  
  @Override
  public Response fetchDataMultipartMime(String url, MultipartParameter... params) {
    return fetchData(url, Method.POST);
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
    setResponse("https://api.foursquare.com/v2/checkins/add?venueId=4f738dace4b06c4afe796dab&ll=34.144744767251765%2C-118.14897413481974", "checkins/add_4.json");
    setResponse("https://api.foursquare.com/v2/checkins/recent", "checkins/recent_1.json");
    setResponse("https://api.foursquare.com/v2/checkins/4de470c0ae60e7f3ac1f0fa7/addcomment?text=That%27s+very+testy%21", "checkins/addcomment_1.json");
    setResponse("https://api.foursquare.com/v2/checkins/4de470c0ae60e7f3ac1f0fa7/deletecomment?commentId=4df3393b14954f21cf2d7543", "checkins/deletecomment_1.json");
    setResponse("https://api.foursquare.com/v2/users/self", "users/id_1.json");
    setResponse("https://api.foursquare.com/v2/users/1504602", "users/id_2.json");
    setResponse("https://api.foursquare.com/v2/users/gibberish", "users/id_3.json");
    setResponse("https://api.foursquare.com/v2/users/7613255/request", "users/request_1.json");
    setResponse("https://api.foursquare.com/v2/users/7613255/unfriend", "users/unfriend_1.json");
    setResponse("https://api.foursquare.com/v2/users/10078668/approve", "users/approve_1.json");
    setResponse("https://api.foursquare.com/v2/users/10078668/deny", "users/deny_1.json");
    setResponse("https://api.foursquare.com/v2/users/self/checkins", "users/checkins_1.json");
    setResponse("https://api.foursquare.com/v2/users/self/friends", "users/friends_1.json");
    setResponse("https://api.foursquare.com/v2/users/search?twitter=naveen", "users/search_1.json");
    setResponse("https://api.foursquare.com/v2/users/requests", "users/requests_1.json");
    setResponse("https://api.foursquare.com/v2/users/leaderboard", "users/leaderboard_1.json");
    setResponse("https://api.foursquare.com/v2/users/self/badges", "users/badges_1.json");
    setResponse("https://api.foursquare.com/v2/users/self/tips?sort=recent", "users/tips_1.json");
    setResponse("https://api.foursquare.com/v2/users/self/todos?sort=recent", "users/todos_1.json");
    setResponse("https://api.foursquare.com/v2/users/self/venuehistory", "users/venuehistory_1.json");
    setResponse("https://api.foursquare.com/v2/users/10078668/setpings?value=true", "users/setpings_1.json");
    setResponse("https://api.foursquare.com/v2/venues/5104", "venues/id_1.json");
    setResponse("https://api.foursquare.com/v2/venues/4cb38bf20cdc721ea943234f", "venues/id_2.json");
    setResponse("https://api.foursquare.com/v2/venues/4eb0479b93ad8dcab730a3c3", "venues/id_3.json");
    setResponse("https://api.foursquare.com/v2/venues/categories", "venues/categories_1.json");
    setResponse("https://api.foursquare.com/v2/venues/search?ll=40.7%2C-74", "venues/search_1.json");
    setResponse("https://api.foursquare.com/v2/venues/search?ll=40.7%2C-74.0", "venues/search_2.json");
    setResponse("https://api.foursquare.com/v2/venues/trending?ll=40.7%2C-74", "venues/trending_1.json");
    setResponse("https://api.foursquare.com/v2/venues/add?name=Apuv%C3%A4lineyksikk%C3%B6+%2F+Moision+toimipiste&address=Moisiontie+11+b&city=Mikkeli&state=Etel%C3%A4-Savo&zip=50520&phone=0443516511&ll=61.677701%2C27.272585&primaryCategoryId=4bf58dd8d48988d104941735", "venues/add_1.json");
    setResponse("https://api.foursquare.com/v2/venues/4de88f43d22d09215a1f73e1/proposeedit?name=Apuv%C3%A4lineyksikk%C3%B6+%2F+Moision+toimipiste&address=Moisiontie+11+b&city=Mikkeli&state=Etel%C3%A4-Savo&zip=50520&phone=0443516511&ll=61.677701%2C27.272585&primaryCategoryId=4bf58dd8d48988d104941735", "venues/proposeedit_1.json");
    setResponse("https://api.foursquare.com/v2/venues/4de88f43d22d09215a1f73e1/edit?name=Apuv%C3%A4lineyksikk%C3%B6+%2F+Moision+toimipiste&address=Moisiontie+11+b&city=Mikkeli&state=Etel%C3%A4-Savo&zip=50520&phone=0443516511&ll=61.677701%2C27.272585&categoryId=4bf58dd8d48988d104941735", "venues/edit_1.json");
    setResponse("https://api.foursquare.com/v2/venues/explore?ll=10.7%2C-74", "venues/explore_1.json");
    setResponse("https://api.foursquare.com/v2/venues/explore?ll=40.7%2C-74", "venues/explore_2.json");
    setResponse("https://api.foursquare.com/v2/venues/5104/herenow", "venues/herenow_1.json");
    setResponse("https://api.foursquare.com/v2/venues/3fd66200f964a52074e31ee3/links", "venues/links_1.json");
    setResponse("https://api.foursquare.com/v2/venues/4beb18a36295c9b669478708/flag?problem=closed", "venues/flag_1.json");
    setResponse("https://api.foursquare.com/v2/venues/5104/tips?sort=recent", "venues/tips_1.json");
    setResponse("https://api.foursquare.com/v2/venues/4b81ea40f964a520e0c330e3/marktodo", "venues/marktodo_1.json");
    setResponse("https://api.foursquare.com/v2/venues/43695300f964a5208c291fe3/photos?group=venue", "venues/photos_1.json");
    setResponse("https://api.foursquare.com/v2/tips/4b5e662a70c603bba7d790b4", "tips/id_1.json");
    setResponse("https://api.foursquare.com/v2/tips/add?venueId=4bb73a402ea19521b1a6ac2f&text=Wonderful+festival+called+Beautiful+Days+here+at+19%2C+20+%26+21+of+August+2011&url=http%3A%2F%2Fwww.beautifuldays.org", "tips/add_1.json");
    setResponse("https://api.foursquare.com/v2/tips/4bb8f41970c603bb64bf96b4/marktodo", "tips/marktodo_1.json");
    setResponse("https://api.foursquare.com/v2/tips/search?ll=40.7%2C-74", "tips/search_1.json");
    setResponse("https://api.foursquare.com/v2/tips/4d13a1edf898b1f73ac3e181/markdone", "tips/markdone_1.json");
    setResponse("https://api.foursquare.com/v2/tips/4d13a1edf898b1f73ac3e181/unmark", "tips/unmark_1.json");
    setResponse("https://api.foursquare.com/v2/photos/4d0fb8162d39a340637dc42b", "photos/id_1.json");
    setResponse("https://api.foursquare.com/v2/photos/4df3a350fd286d19d4beb926", "photos/id_2.json");
    setResponse("https://api.foursquare.com/v2/photos/add?checkinId=4de470c0ae60e7f3ac1f0fa7", "photos/add_1.json");
  }
}