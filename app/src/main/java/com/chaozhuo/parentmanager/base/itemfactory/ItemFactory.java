package com.chaozhuo.parentmanager.base.itemfactory;

public class ItemFactory implements IItemFactory {

    @Override
    public int getType(ItemInterface item) {
        return item.getType();
    }
}
