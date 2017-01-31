package org.cachopo.shoppinglist;

//import com.google.appengine.api.datastore.DatastoreService;
//import com.google.appengine.api.datastore.DatastoreServiceFactory;
//import com.google.appengine.api.datastore.Entity;
//import com.google.appengine.api.datastore.Key;
//import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import java.io.IOException;
//import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.googlecode.objectify.ObjectifyService;

public class AddItemServlet extends HttpServlet {

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

    UserService userService = UserServiceFactory.getUserService();
    User user = userService.getCurrentUser();

    String listId = user.getUserId();
    String content = req.getParameter("content");
    ShopItem item = new ShopItem(listId, content);

    ObjectifyService.ofy().save().entity(item).now();

    resp.sendRedirect("/shoppinglist.jsp");
  }
}
