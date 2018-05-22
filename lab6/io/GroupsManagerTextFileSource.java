package io;

import humanResources.EmployeeFactory;
import humanResources.EmployeeGroup;

import java.io.*;

public class GroupsManagerTextFileSource extends EmployeeFactory implements FileSource {
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
    public EmployeeGroup load(EmployeeGroup employeeGroup) throws IOException {
        FileReader filein = new FileReader(getPath());
        StreamTokenizer in = new StreamTokenizer(filein);
        StringBuilder result = new StringBuilder();
        while  (in.nextToken() != StreamTokenizer.TT_EOF)   {
                result.append(in.nval);
        }


        return employeeGroup;
    }

    @Override
    public void store(EmployeeGroup employeeGroup) throws FileNotFoundException {
        PrintWriter printWriter = new PrintWriter(getPath());
        printWriter.print(employeeGroup.getName());
        printWriter.print(employeeGroup.toString());
        printWriter.close();
    }

    @Override
    public boolean delete(EmployeeGroup employeeGroup) {
        File file = new File(getPath());
        return file.delete();
    }

    @Override
    public void create(EmployeeGroup employeeGroup) throws IOException {
        File file = new File(getPath());
        if(file.createNewFile()) {
            PrintWriter printWriter = new PrintWriter(getPath());
            printWriter.print(employeeGroup.getName());
            printWriter.print(employeeGroup.toString());
            printWriter.close();
        }
    }
}
