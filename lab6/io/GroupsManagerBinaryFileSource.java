package io;

import humanResources.Department;
import humanResources.EmployeeGroup;

import java.io.*;

public class GroupsManagerBinaryFileSource implements FileSource {
    @Override
    public void setPath(String path) {

    }

    @Override
    public String getPath() {
        return null;
    }

    @Override
    public EmployeeGroup load(EmployeeGroup employeeGroup) throws IOException, ClassNotFoundException {
        DataInputStream dos = new DataInputStream(new FileInputStream(getPath()));
        String str = dos.readUTF();


        return employeeGroup;
    }

    @Override
    public void store(EmployeeGroup employeeGroup) throws IOException {
        DataOutputStream dos = new DataOutputStream(new FileOutputStream(getPath()));
        dos.writeBytes(employeeGroup.getName());
        dos.writeBytes(employeeGroup.toString());
        dos.flush();
        dos.close();
    }

    @Override
    public boolean delete(EmployeeGroup employeeGroup) {
        return false;
    }

    @Override
    public void create(EmployeeGroup employeeGroup) throws IOException {
        File file = new File(getPath());
        if(file.createNewFile()) {
            DataOutputStream dos = new DataOutputStream(new FileOutputStream(getPath()));
            dos.writeBytes(employeeGroup.getName());
            dos.writeBytes(employeeGroup.toString());
        }
    }
}
