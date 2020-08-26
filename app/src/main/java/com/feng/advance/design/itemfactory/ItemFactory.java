package com.feng.advance.design.itemfactory;

public class ItemFactory implements IItemFactory {

    @Override
    public int getType(ItemInterface item) {
        return item.getType();
    }
}
