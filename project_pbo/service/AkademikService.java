package project_pbo.service;

import project_pbo.model.Student;
import project_pbo.model.Teacher;
import java.util.ArrayList;

public class AkademikService<T>{
    private ArrayList<T> dataList = new ArrayList<>();

    public void add(T item) {
        dataList.add(item);
    }

    public void displayAll() {
        for (T item : dataList) {
            System.out.println(item);
        }
    }

    public ArrayList<T> getDataList() {
        return dataList;
    }
}
