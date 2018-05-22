package io;

import humanResources.EmployeeGroup;

import java.io.IOException;

public interface Source<T> {
    public EmployeeGroup load(T t) throws IOException, ClassNotFoundException;
    public void store(T t) throws IOException;
    boolean delete(T t);
    void create(T t) throws IOException;
}
