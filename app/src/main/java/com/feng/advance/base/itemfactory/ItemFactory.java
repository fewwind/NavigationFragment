package com.feng.advance.base.itemfactory;

public class ItemFactory implements IItemFactory {

    @Override
    public int getType(ItemInterface item) {
        return item.getType();
    }
}
