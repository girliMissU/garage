package com.garage.admin.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @Author: LiFan
 * @Date: 2020/6/16
 */

@Entity(name = "img")
public class imgTest {

    @Id
    @GeneratedValue
    private Integer id;

    private String url;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}


