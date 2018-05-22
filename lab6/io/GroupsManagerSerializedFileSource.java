package io;

import humanResources.EmployeeGroup;

import java.io.*;

public class GroupsManagerSerializedFileSource implements FileSource {
    @Override
    public void setPath(String path) {

    }

    @Override
    public String getPath() {
        return null;
    }


    @Override
    public EmployeeGroup load(EmployeeGroup employeeGroup) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(getPath());
        ObjectInputStream oin = new ObjectInputStream(fis);
        employeeGroup = (EmployeeGroup) oin.readObject();
        return employeeGroup;
    }

    @Override
    public void store(EmployeeGroup employeeGroup) throws IOException {
        FileOutputStream fos = new FileOutputStream(getPath());
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(employeeGroup);
        oos.flush();
        oos.close();
    }

    @Override
    public boolean delete(EmployeeGroup employeeGroup) {
        return false;
    }

    @Override
    public void create(EmployeeGroup employeeGroup) throws IOException {
        File file = new File(getPath());
        if(file.createNewFile()) {
            FileOutputStream fos = new FileOutputStream(getPath());
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(employeeGroup);
            oos.flush();
            oos.close();
        }
    }
}
