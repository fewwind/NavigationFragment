package com.chaozhuo.parentmanager.bean;

import java.io.Serializable;

/**
 * Created by fewwind on 19-3-12.
 */

public class OnLineConfigBean implements Serializable {
    public String name;
    public String value;
    public String version;

    @Override
    public String toString() {
        return "OnLineConfigBean{" +
                "name='" + name + '\'' +
                ", value=" + value +
                ", version='" + version + '\'' +
                '}';
    }
}
