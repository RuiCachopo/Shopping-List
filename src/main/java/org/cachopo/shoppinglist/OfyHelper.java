package org.cachopo.shoppinglist;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;

import javax.servlet.ServletContextListener;
import javax.servlet.ServletContextEvent;

public class OfyHelper implements ServletContextListener {
  public void contextInitialized(ServletContextEvent event) {

    ObjectifyService.register(ShopItem.class);
    ObjectifyService.register(ShoppingList.class);
  }

  public void contextDestroyed(ServletContextEvent event) {}
}
