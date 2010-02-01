/* 
 * de.dan_nrw.web
 * 
 * Copyright (C) 2010, Daniel Czerwonk <d.czerwonk@googlemail.com>
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
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