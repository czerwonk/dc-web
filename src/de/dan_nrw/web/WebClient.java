package de.dan_nrw.web;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

import org.apache.http.ProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;


/**
 * @author Daniel Czerwonk
 */
public class WebClient {
    
    private final UserAgent userAgent;
    
    
    /**
     * Creates a new instance of WebClient
     * (uses Firefox 3 user agent signature as default)
     */
    private WebClient() {
        this.userAgent = UserAgent.FIREFOX_3;
    }
    
    /**
     * Creates a new instance of WebClient
     * @param userAgent
     */
    private WebClient(UserAgent userAgent) {
        super();
        
        this.userAgent = userAgent;
    }


    /**
     * Sends a http request and returns repsonse as string
     * @param uri URI to use
     * @return
     * @throws IOException if response handling fails
     * @throws ProtocolException if request returns an error
     */
    public String getResponseString(URI uri) throws IOException, ProtocolException {
        return this.sendRequest(uri, new StringResponseHandler());
    }
    
    /**
     * Sends a http request and stores its response stream in specified file
     * @param uri URI to use
     * @param targetFile File to store data downloaded
     * @throws IOException if response handling fails
     * @throws ProtocolException if request returns an error
     */
    public void downloadFile(URI uri, File targetFile) throws IOException, ProtocolException {
        this.sendRequest(uri, new FileDownloadResponseHandler(targetFile));
    }
    
    /**
     * Sends a http request and returns its reponse stream
     * @param uri URI to use
     * @return Stream provided by response
     * @throws IOException if response handling fails
     * @throws ProtocolException if request returns an error
     */
    public InputStream getResponseStream(URI uri) throws IOException, ProtocolException {
        return this.sendRequest(uri, new StreamResponseHandler());
    }
    
    /**
     * Sends a http request and handles response using specified ResponseHandler
     * @param <T> Type of return
     * @param uri URI to use
     * @param responseHandler Response handled used for handling response
     * @return
     * @throws IOException if response handling fails
     * @throws ProtocolException if request returns an error
     */
    public <T> T sendRequest(URI uri, ResponseHandler<T> responseHandler) throws IOException, ProtocolException {
        HttpGet request = new HttpGet(uri);
        request.addHeader("User-agent", this.userAgent.getHeaderValue());
        
        DefaultHttpClient client = new DefaultHttpClient();
        return client.execute(request, responseHandler);
    }
}