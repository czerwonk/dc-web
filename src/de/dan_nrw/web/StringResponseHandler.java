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
