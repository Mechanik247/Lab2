package io;

import humanResources.EmployeeGroup;

public class GropsManagerFileSource implements FileSource{

    private String path;

    @Override
    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public EmployeeGroup load(EmployeeGroup employeeGroup) {

        return employeeGroup;
    }

    @Override
    public void store(EmployeeGroup employeeGroup) {

    }

    @Override
    public boolean delete(EmployeeGroup employeeGroup) {
        return false;
    }

    @Override
    public void create(EmployeeGroup employeeGroup) {

    }
}
