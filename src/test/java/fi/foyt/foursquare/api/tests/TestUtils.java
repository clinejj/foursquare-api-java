package fi.foyt.foursquare.api.tests;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import fi.foyt.foursquare.api.FoursquareApi;

public class TestUtils {

  public static FoursquareApi getAnonymousFoursquareApi() {
    FoursquareApi foursquareApi = new FoursquareApi(CLIENT_ID, CLIENT_SECRET, REDIRECT_URL, new TestIO());
    foursquareApi.setSkipNonExistingFields(false);
    return foursquareApi;
  }
  
  public static FoursquareApi getAuthenticatedFoursquareApi() {
    FoursquareApi foursquareApi = new FoursquareApi(CLIENT_ID, CLIENT_SECRET, REDIRECT_URL, OAUTH, new TestIO());
    foursquareApi.setSkipNonExistingFields(false);
    return foursquareApi;
  }
  
  public static byte[] getFileData(String path) throws IOException {
    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    
    byte[] buf = new byte[1024];
    int l = 0;
    InputStream in = TestUtils.class.getResourceAsStream(path);
   
    while ((l = in.read(buf)) > 0) {
      bos.write(buf, 0, l);
    }
    
    return bos.toByteArray();
  }
  
  private final static String CLIENT_ID = "FAKE_CLIENT_ID";
  private final static String CLIENT_SECRET = "FAKE_CLIENT_SECRET";
  private final static String REDIRECT_URL = "FAKE_REDIRECT_URL";
  private final static String OAUTH = "FAKE_OAUTH";
}
