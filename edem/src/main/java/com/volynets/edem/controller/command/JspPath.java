package com.volynets.edem.controller.command;

/**
 * This enum contains different paths to jsp.
 * 
 * @author Pavel Volynets
 * @version 1.0
 */
public enum JspPath {
    INDEX("/index.jsp"),
    ABOUT("/jsp/user/about.jsp"),
    ERROR("/jsp/error.jsp"),
    SIGN_IN("/jsp/sign_in.jsp"),
    
    HOME_USER("/jsp/user/home_user.jsp"),
    HOME_ADMIN("/jsp/admin/home_admin.jsp"),
	VIEW_ACTIONS("/jsp/view_actions.jsp"),
	VIEW_ANIMALS("/jsp/view_animals.jsp"),
	ACTION("/jsp/user/action.jsp"),
	ACCOUNT("/jsp/user/personal.jsp"),
	HISTORY("/jsp/user/history.jsp"),
		
	VIEW_ACTIONS_ADMIN("/jsp/admin/view_actions.jsp"),
	VIEW_ACCOUNTS("/jsp/admin/view_accounts.jsp"),
		
    REGISTRATION("/jsp/registration.jsp"),
    ADD_ACTION("/jsp/admin/add_action.jsp"),
	ADD_ANIMAL("/jsp/admin/add_animal.jsp");

    private String url;

    JspPath(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
