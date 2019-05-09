package com.hotel.pojo_classes;

public class pojo_subcategory {
    int subcategory_id;
    String subcategoty;

    public pojo_subcategory(int subcategory_id, String subcategoty) {
        this.subcategory_id = subcategory_id;
        this.subcategoty = subcategoty;
    }

    public int getSubcategory_id() {
        return subcategory_id;
    }

    public void setSubcategory_id(int subcategory_id) {
        this.subcategory_id = subcategory_id;
    }

    public String getSubcategoty() {
        return subcategoty;
    }

    public void setSubcategoty(String subcategoty) {
        this.subcategoty = subcategoty;
    }
}
