package com.volynets.edem.controller.command;

/**
 * This enum contains different paths to jsp.
 * 
 * @author Pavel Volynets
 * @version 1.0
 */
public enum JspPath {
    INDEX("/index.jsp"),
 //   REGISTRATION("/jsp/registration.jsp"),
 //   HOME("/jsp/common/home.jsp"),
    ERROR("/jsp/error.jsp"),
    SIGN_IN("/jsp/sign_in.jsp"),
	WELCOME("/jsp/welcome.jsp");
    
    private String url;

    JspPath(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
