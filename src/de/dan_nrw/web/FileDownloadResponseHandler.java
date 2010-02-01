package de.dan_nrw.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;


/**
 * @author Daniel Czerwonk
 *
 */
class FileDownloadResponseHandler extends HttpResponseHandler<Void> {

    private final File targetFile;

    
    /**
     * Creates a new instance of FileDownloadResponseHandler
     * @param targetFile File to store downloaded data
     */
    FileDownloadResponseHandler(File targetFile) {
        super();
        
        this.targetFile = targetFile;
    }


    /* (non-Javadoc)
     * @see de.dan_nrw.web.HttpResponseHandler#handleResponseInternal(org.apache.http.HttpResponse)
     */
    @Override
    protected Void handleResponseInternal(HttpResponse response) throws ClientProtocolException, IOException {
        int bytesRead = 0;
        byte[] buffer = new byte[16384];
        
        InputStream stream = null; 
        FileOutputStream output = null;
        
        try {
            output = new FileOutputStream(this.targetFile, false);
            stream = response.getEntity().getContent();
            
            while ((bytesRead = stream.read(buffer)) > 0) {
                output.write(buffer, 0, bytesRead);
            }
        }
        finally {
            if (output != null) {
                try {
                    output.close();    
                }
                catch (IOException ex) {
                    // would hide exception thrown in outer try block
                }
            }
            
            if (stream != null) {
                try {
                    stream.close();   
                }
                catch (IOException ex) {
                    // would hide exception thrown in outer try block
                }
            }
        }
        
        return null;
    }
}