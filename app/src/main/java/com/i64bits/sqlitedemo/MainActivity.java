package com.i64bits.sqlitedemo;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.i64bits.sqlitedemo.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private DBHelper dbHelper;

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        dbHelper = new DBHelper(this);

        updateUsersList();
        setListener();
    }

    public void setListener()
    {
        binding.btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertUserIntoDatabase();
                updateUsersList();
            }
        });
    }

    public void insertUserIntoDatabase()
    {
        User user = new User(binding.edtName.getText().toString(),
                            Integer.parseInt(binding.edtAge.getText().toString()),
                            binding.edtLocation.getText().toString());

        dbHelper.insert(user);
    }

    public void updateUsersList()
    {
        final List<User> users = dbHelper.getUsers();

        Collections.reverse(users);

        List<String> values = new ArrayList<>();

        for (User user: users)
        {
            values.add(user.getName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);

        binding.lstUsers.setAdapter(adapter);
    }
}
