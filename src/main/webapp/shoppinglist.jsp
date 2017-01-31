<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>

<%@ page import="org.cachopo.shoppinglist.ShopItem" %>
<%@ page import="org.cachopo.shoppinglist.ShoppingList" %>
<%@ page import="com.googlecode.objectify.Key" %>
<%@ page import="com.googlecode.objectify.ObjectifyService" %>

<%@ page import="java.util.List" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <link type="text/css" rel="stylesheet" href="/stylesheets/main.css"/>
</head>

<body>

<%
    UserService userService = UserServiceFactory.getUserService();
    User user = userService.getCurrentUser();
    String userId = user.getUserId();
    pageContext.setAttribute("user", user);
%>

<p>Hello, ${fn:escapeXml(user.nickname)}! (You can
    <a href="<%= userService.createLogoutURL(request.getRequestURI()) %>">sign out</a>.)</p>
<h1>Shopping list:</h1>

<%
      Key<ShoppingList> theList = Key.create(ShoppingList.class, userId);

      List<ShopItem> items = ObjectifyService.ofy()
          .load()
          .type(ShopItem.class)
          .ancestor(theList)
          .order("date")
          .list();

    if (!items.isEmpty()) {
%>
<ul>
<%
        for (ShopItem item : items) {
            pageContext.setAttribute("item_content", item.content);
            pageContext.setAttribute("item_id", item.id);
%>
<li>
  <form action="/deleteOneItem" method="post">
    ${fn:escapeXml(item_content)}
    <input type="submit" value="Delete item"/>
    <input type="hidden" name="itemId" value="${item_id}"/>
  </form>
</li>
<%
        }
%>
</ul>
<%
    }
%>

<form action="/addItem" method="post">
    <div><input type="text" name="content"/></div>
    <div><input type="submit" value="Add Item"/></div>
</form>

<form action="/deleteAllItems" method="post">
    <div><input type="submit" value="Delete all items"/>
</form>

</body>
</html>
