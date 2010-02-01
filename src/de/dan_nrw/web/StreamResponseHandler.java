package de.dan_nrw.web;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;


/**
 * @author Daniel Czerwonk
 */
class StreamResponseHandler extends HttpResponseHandler<InputStream> {

    /* (non-Javadoc)
     * @see de.dan_nrw.web.HttpResponseHandler#handleResponseInternal(org.apache.http.HttpResponse)
     */
    @Override
    protected InputStream handleResponseInternal(HttpResponse response) throws ClientProtocolException, IOException {
        return response.getEntity().getContent();
    }

}
