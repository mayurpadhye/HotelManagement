package com.hotel;

public class ItemListPojo {
    String id,item_name,item_image,item_contents;

    public ItemListPojo(String id, String item_name, String item_image, String item_contents) {
        this.id = id;
        this.item_name = item_name;
        this.item_image = item_image;
        this.item_contents = item_contents;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getItem_image() {
        return item_image;
    }

    public void setItem_image(String item_image) {
        this.item_image = item_image;
    }

    public String getItem_contents() {
        return item_contents;
    }

    public void setItem_contents(String item_contents) {
        this.item_contents = item_contents;
    }
}
