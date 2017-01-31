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


public class DeleteAllItemsServlet extends HttpServlet {

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

    UserService userService = UserServiceFactory.getUserService();
    User user = userService.getCurrentUser();

    String listId = user.getUserId();

    Key<ShoppingList> theList = Key.create(ShoppingList.class, listId);
    List<ShopItem> items = ObjectifyService.ofy()
        .load()
        .type(ShopItem.class)
        .ancestor(theList)
        .list();

    ObjectifyService.ofy().delete().entities(items).now();

    resp.sendRedirect("/shoppinglist.jsp");
  }
}
