package cz.cvut.fit.si1.skld.client.network.http;

import org.apache.http.client.fluent.Request;

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
