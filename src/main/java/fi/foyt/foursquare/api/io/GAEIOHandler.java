package fi.foyt.foursquare.api.io;

import static com.google.appengine.api.urlfetch.FetchOptions.Builder.doNotValidateCertificate;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import com.google.appengine.api.urlfetch.HTTPMethod;
import com.google.appengine.api.urlfetch.HTTPRequest;
import com.google.appengine.api.urlfetch.HTTPResponse;
import com.google.appengine.api.urlfetch.URLFetchService;
import com.google.appengine.api.urlfetch.URLFetchServiceFactory;

import fi.foyt.foursquare.api.io.IOHandler;
import fi.foyt.foursquare.api.io.Method;
import fi.foyt.foursquare.api.io.Response;

/**
 * Handler for appengine which needs the doNotValidateCertificate nonsense.
 * @author rmangi
 *
 */
public class GAEIOHandler extends IOHandler {

	@Override
	public Response fetchData(String url, Method method) {
		try {
			URL aUrl = new URL(url);

			HTTPMethod httpMethod = HTTPMethod.GET;
			switch (method) {
			case POST:
				httpMethod = HTTPMethod.POST;
				break;
			}

			HTTPRequest httpRequest = new HTTPRequest(aUrl, httpMethod,
					doNotValidateCertificate().setDeadline(10d));
			URLFetchService service = URLFetchServiceFactory
					.getURLFetchService();
			HTTPResponse response = service.fetch(httpRequest);

			return new Response(new String(response.getContent(), "UTF-8"),
					response.getResponseCode(), "");
		} catch (MalformedURLException e) {
			return new Response("", 400, "Malformed URL: " + url);
		} catch (IOException e) {
			return new Response("", 500, e.getMessage());
		}
	}

	@Override
	/**
	 * Not yet implemented... 
	 */
	public Response fetchDataMultipartMime(String url,
			MultipartParameter... params) {
		// TODO Auto-generated method stub
		return null;
	}

}