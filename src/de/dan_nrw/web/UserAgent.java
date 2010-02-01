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


/**
 * @author Daniel Czerwonk
 */
public enum UserAgent {

    FIREFOX_3("Mozilla/5.0 (Windows; U; Windows NT 6.0; en-US; rv:1.9.0.6) Gecko/2009011913 Firefox/3.0.6"),
    INTERNET_EXPLORER_8("Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0)");
    
    private final String headerValue;

    
    /**
     * Creates a new instance of UserAgent
     * @param headerValue
     */
    private UserAgent(String headerValue) {
        this.headerValue = headerValue;
    }
    
    
    public String getHeaderValue() {
        return this.headerValue;
    }
}
