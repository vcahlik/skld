package cz.cvut.fit.project.skld.client.http;

import org.apache.http.client.fluent.Request;

/**
 * Rozlisuje HTTP pozadavky
 */
public enum HTTPMethod {
    GET, POST, PUT, DELETE;

    Request requestForMethod(String url) {
        switch (this) {
            case GET:       return Request.Get(url);
            case POST:      return Request.Post(url);
            case PUT:       return Request.Put(url);
            case DELETE:    return Request.Delete(url);
        }
        throw new UnsupportedOperationException();
    }
}
