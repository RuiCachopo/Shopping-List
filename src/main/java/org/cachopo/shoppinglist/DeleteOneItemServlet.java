package org.cachopo.shoppinglist;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import java.io.IOException;
import com.googlecode.objectify.Key;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.googlecode.objectify.ObjectifyService;
import java.util.List;


public class DeleteOneItemServlet extends HttpServlet {

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

    UserService userService = UserServiceFactory.getUserService();
    User user = userService.getCurrentUser();

    String listId = user.getUserId();
    Key<ShoppingList> theList = Key.create(ShoppingList.class, listId);
    long itemId = Long.parseLong(req.getParameter("itemId"));

    ObjectifyService.ofy().delete().type(ShopItem.class).parent(theList).id(itemId).now();

    resp.sendRedirect("/shoppinglist.jsp");
  }
}
