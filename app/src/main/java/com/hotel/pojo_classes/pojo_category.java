package com.hotel.pojo_classes;

import java.util.List;

public class pojo_category {
    int category_id;
    String categoty,image;
List<pojo_subcategory> list;
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public pojo_category(int category_id, String categoty, String image,List<pojo_subcategory> list) {
        this.category_id = category_id;
        this.categoty = categoty;
        this.image=image;
        this.list=list;
    }

    public List<pojo_subcategory> getList() {
        return list;
    }

    public void setList(List<pojo_subcategory> list) {
        this.list = list;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getCategoty() {
        return categoty;
    }

    public void setCategoty(String categoty) {
        this.categoty = categoty;
    }
}
