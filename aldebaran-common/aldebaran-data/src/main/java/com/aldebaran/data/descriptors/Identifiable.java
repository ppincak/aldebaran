package com.aldebaran.data.descriptors;

import java.io.Serializable;


public interface Identifiable<TKey extends Serializable> {

    TKey getId();

    void setId(TKey id);
}