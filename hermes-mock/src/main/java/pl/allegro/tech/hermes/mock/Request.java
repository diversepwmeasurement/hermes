package pl.allegro.tech.hermes.mock;

import com.github.tomakehurst.wiremock.http.RequestMethod;
import com.github.tomakehurst.wiremock.verification.LoggedRequest;

import java.util.HashMap;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

public class Request {
    enum Method {
        GET,
        POST,
        PUT,
        DELETE,
        PATCH,
        OPTIONS,
        HEAD,
        TRACE
    }

    private String url;
    private Method method;
    private byte[] body;
    private Map<String, String> headers;

    public Request(LoggedRequest loggedRequest) {
        this.url = loggedRequest.getUrl();
        this.method = getRequestMethod(loggedRequest.getMethod());
        this.body = loggedRequest.getBody();
        this.headers = loggedRequest.getAllHeaderKeys().stream()
                .collect(toMap(key -> key, loggedRequest::getHeader));
    }

    public String getUrl() {
        return url;
    }

    public Method getMethod() {
        return method;
    }

    public byte[] getBody() {
        return body;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    private Method getRequestMethod(RequestMethod req) {
        Map<RequestMethod, Method> map = new HashMap<>();
        map.put(RequestMethod.GET, Method.GET);
        map.put(RequestMethod.POST, Method.POST);
        map.put(RequestMethod.PUT, Method.PUT);
        map.put(RequestMethod.DELETE, Method.DELETE);
        map.put(RequestMethod.PATCH, Method.PATCH);
        map.put(RequestMethod.OPTIONS, Method.OPTIONS);
        map.put(RequestMethod.HEAD, Method.HEAD);
        map.put(RequestMethod.TRACE, Method.TRACE);
        return map.get(req);
    }
}
