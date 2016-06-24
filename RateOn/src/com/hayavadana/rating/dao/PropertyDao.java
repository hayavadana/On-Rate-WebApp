package com.hayavadana.rating.dao;

import java.util.List;

import com.hayavadana.rating.model.Property;

public interface PropertyDao {
public Property getProperty(String cat,String name);
public List<Property> getPropertyList(String category);
}
