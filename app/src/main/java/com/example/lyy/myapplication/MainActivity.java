package com.example.lyy.myapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private MyHelper helper;
    private SQLiteDatabase db;
    private SQLiteDatabase db1;
    private List<String> nameList;
    private List<Double> salaryList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helper = new MyHelper(this,"lyy",null,1);
        db = helper.getWritableDatabase();
        db1 = helper.getWritableDatabase();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }

    public void submit(View view) {
        EditText ed1 = findViewById(R.id.nameText);
        EditText ed2  =findViewById(R.id.salaryText);
        String insertSql = "insert into employee (ename,salary) values(?,?)";
        db.execSQL(insertSql,new Object[]{ed1.getText().toString(),ed2.getText()});
    }
    public void query(View view){
        List<Employee> employees = new ArrayList<Employee>();
        String sql = "select * from employee";
        Cursor cursor = db1.rawQuery(sql,null);
        while (cursor.moveToNext()){
            Employee employee = new Employee();
            String ename = cursor.getString(cursor.getColumnIndex("ename"));
            double salary = cursor.getDouble(cursor.getColumnIndex("salary"));
            employee.setName(ename);
            employee.setSalary(salary);
            employees.add(employee);
        }
        ListView employeelv = findViewById(R.id.employeelv);
        EmployeeAdapter employeeAdapter = new EmployeeAdapter(this,R.layout.empoloyee_activity,employees);
        employeelv.setAdapter(employeeAdapter);
    }
}
