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
