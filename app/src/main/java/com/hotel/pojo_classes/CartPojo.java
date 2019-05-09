package com.hotel.pojo_classes;

public class CartPojo {
    String item_id,item_name,item_image,item_ingredients,item_instruction;

    public CartPojo(String item_id, String item_name, String item_image, String item_ingredients, String item_instruction) {
        this.item_id = item_id;
        this.item_name = item_name;
        this.item_image = item_image;
        this.item_ingredients = item_ingredients;
        this.item_instruction = item_instruction;
    }

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
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

    public String getItem_ingredients() {
        return item_ingredients;
    }

    public void setItem_ingredients(String item_ingredients) {
        this.item_ingredients = item_ingredients;
    }

    public String getItem_instruction() {
        return item_instruction;
    }

    public void setItem_instruction(String item_instruction) {
        this.item_instruction = item_instruction;
    }
}
