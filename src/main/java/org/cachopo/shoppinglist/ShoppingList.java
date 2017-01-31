package org.cachopo.shoppinglist;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class ShoppingList {
  @Id public String listId;
}
