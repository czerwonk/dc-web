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
package de.dan_nrw.web.tests;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;

import org.junit.Test;

import static org.mockito.Mockito.*;

import de.dan_nrw.web.HttpResponseHandler;


/**
 * @author Daniel Czerwonk
 *
 */
public class HttpResponseHandlerTests {
   
    @Test(expected=ClientProtocolException.class)
    public void handleResponse_should_throw_ClientProtocolException_on_status_code_higher_399() throws ClientProtocolException, IOException {
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(400);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        
        HttpResponseHandler<Void> handler = new HttpResponseHandler<Void>() {
            
            @Override
            protected Void handleResponseInternal(HttpResponse response) throws ClientProtocolException, IOException {
                return null;
            }
        };
        handler.handleResponse(response);
    }
    
    @Test()
    public void handleResponse_should_not_throw_ClientProtocolException_on_status_code_lesser_400() throws ClientProtocolException, IOException {
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(399);
        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        
        HttpResponseHandler<Void> handler = new HttpResponseHandler<Void>() {
            
            @Override
            protected Void handleResponseInternal(HttpResponse response) throws ClientProtocolException, IOException {
                return null;
            }
        };
        handler.handleResponse(response);
    }
}