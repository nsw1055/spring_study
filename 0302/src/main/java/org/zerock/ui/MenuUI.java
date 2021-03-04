package org.zerock.ui;

import org.zerock.service.MenuService;

public class MenuUI {

	private MenuService service;

	public void setService(MenuService service) {
		this.service = service;
	}

	@Override
	public String toString() {
		return "MenuUI [service=" + service + "]";
	}
	
}
