package com.tcs.hack.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "resource")
public class Resource implements Serializable{

    @Id
    @Column(name = "resource_id", nullable=false)
    private int resourceId;
    @Column(name = "resource_name", nullable=false)
    private String resourceName;

    public Resource (){

    }
    public Resource(int resourceId) {
        super();
        this.resourceId = resourceId;
    }
    public Resource(int resourceId, String resourceName) {
        super();
        this.resourceId = resourceId;
        this.resourceName = resourceName;
    }

    public int getResourceId() {
        return resourceId;
    }
    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }
    public String getResourceName() {
        return resourceName;
    }
    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

}
