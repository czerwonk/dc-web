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

import java.net.URI;

import org.apache.http.HttpResponse;
import org.junit.Test;

import static org.mockito.Mockito.*;

import de.dan_nrw.web.HttpResponseHandler;
import de.dan_nrw.web.WebClient;


/**
 * @author Daniel Czerwonk
 */
public class WebClientTests {
    
    @Test
    @SuppressWarnings("unchecked")
    public void retrieving_data_from_valid_uri_should_not_throw_exception() throws Exception {
        WebClient webClient = new WebClient();
        URI uri = new URI("http://www.google.com");
        HttpResponseHandler<Void> responseHandler = mock(HttpResponseHandler.class);
        when(responseHandler.handleResponse(any(HttpResponse.class))).thenReturn(null);
        
        webClient.sendRequest(uri, responseHandler);
    }
    
    @Test
    public void getResponseString_should_not_throw_exception_invoked_with_valid_uri() throws Exception {
        WebClient webClient = new WebClient();
        URI uri = new URI("http://www.google.com");
        
        webClient.getResponseString(uri);
    }
}
