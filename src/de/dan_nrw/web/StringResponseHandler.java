package de.dan_nrw.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;


/**
 * @author Daniel Czerwonk
 */
class StringResponseHandler extends HttpResponseHandler<String> {

    /* (non-Javadoc)
     * @see de.dan_nrw.web.HttpResponseHandler#handleResponseInternal(org.apache.http.HttpResponse)
     */
    @Override
    protected String handleResponseInternal(HttpResponse response) throws ClientProtocolException, IOException {
        BufferedReader reader = null;
        
        try {
            reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            
            int bytesRead = 0;
            char[] buffer = new char[2048];
            StringBuilder stringBuilder = new StringBuilder();
            
            while ((bytesRead = reader.read(buffer)) > 0) {
                stringBuilder.append(buffer, 0, bytesRead);
            }
            
            return stringBuilder.toString();
        }
        finally {
            if (reader != null) {
                try {
                    reader.close();    
                }
                catch (IOException ex) {
                    // would hide exception thrown in outer try block
                    ex.printStackTrace(System.err);
                }
            }
        }
    }
}
