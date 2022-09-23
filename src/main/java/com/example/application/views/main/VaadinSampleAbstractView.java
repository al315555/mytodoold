package com.example.application.views.main;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.RouterLink;

public abstract class VaadinSampleAbstractView extends AppLayout {
	private Tabs tabs = new Tabs();
	
	public VaadinSampleAbstractView() {
		DrawerToggle toggle = new DrawerToggle();

		H1 title = new H1("Vaadin Sample App");
		title.getStyle().set("font-size", "var(--lumo-font-size-l)").set("margin", "0");

		Tabs tabs = getTabs();

		addToDrawer(tabs);
		addToNavbar(toggle, title);
	}

	private Tabs getTabs() {
	    tabs = new Tabs();
	    tabs.add(
	      createTab(VaadinIcon.USERS, "Person data", 0, PersonDataView.class),
	      createTab(VaadinIcon.TASKS, "Tasks", 1, TasksView.class),
	      createTab(VaadinIcon.ABACUS, "Notification", 2, NotificationView.class)
	    );
	    tabs.setOrientation(Tabs.Orientation.VERTICAL);
	    return tabs;
	  }
	
	private Tab createTab(VaadinIcon viewIcon, String viewName, int tabIndex, Class<? extends Component> navigationTarget) {
		Icon icon = viewIcon.create();
		icon.getStyle().set("box-sizing", "border-box").set("margin-inline-end", "var(--lumo-space-m)")
				.set("margin-inline-start", "var(--lumo-space-xs)").set("padding", "var(--lumo-space-xs)");

		RouterLink link = new RouterLink();
		link.add(icon, new Span(viewName));
		// Demo has no routes
		link.setRoute(navigationTarget);
		link.setTabIndex(tabIndex);

		return new Tab(link);
	}
	
	protected void setSelectedTab(final int indexSelected) {
		tabs.setSelectedIndex(indexSelected);
	}

}