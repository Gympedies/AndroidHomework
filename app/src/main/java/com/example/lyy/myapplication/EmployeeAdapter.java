package com.example.lyy.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class EmployeeAdapter extends ArrayAdapter<Employee> {
    private List<Employee> data;

    public EmployeeAdapter(@NonNull Context context, int resource, @NonNull List<Employee> data) {
        super(context, resource, data);
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.empoloyee_activity,parent,false);
        Employee employee = data.get(position);
        TextView nametv = view.findViewById(R.id.nameField);
        TextView salarytv = view.findViewById(R.id.salaryField);
        nametv.setText(employee.getName().toString());
        salarytv.setText(employee.getSalary()+"");
        return view;
    }
}
