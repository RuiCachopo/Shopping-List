package org.cachopo.shoppinglist;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Parent;

import java.lang.String;
import java.util.Date;
import java.util.List;

@Entity
public class ShopItem {
  @Parent Key<ShoppingList> theList;
  @Id public Long id;

  public String content;
  @Index public Date date;

  public ShopItem() {
    date = new Date();
  }

  public ShopItem(String listId, String content) {
    this();
    theList = Key.create(ShoppingList.class, listId);
    /**if( list != null ) {
      theList = Key.create(ShoppingList.class, list);
    } else {
      theList = Key.create(ShoppingList.class, "default");
    }*/
    this.content = content;
  }
}
