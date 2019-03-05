package com.i64bits.sqlitedemo;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.i64bits.sqlitedemo.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private DBHelper dbHelper;

    private ActivityMainBinding binding;

    private List<User> users;
    private User user;

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

        binding.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteSelectedUser();
                updateUsersList();
            }
        });

        binding.lstUsers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                setValues(user = users.get(position));

            }
        });

        binding.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUser();
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

    public void updateUser()
    {
        User user = new User(binding.edtName.getText().toString(),
                Integer.parseInt(binding.edtAge.getText().toString()),
                binding.edtLocation.getText().toString());

        dbHelper.updateUser(this.user.getId(), Utils.modelToContentValues(user));
    }

    public void updateUsersList()
    {
        users = dbHelper.getUsers();

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

    public void deleteSelectedUser()
    {
        String userName = binding.edtName.getText().toString();

        if(!Utils.isNullOrEmpty(userName)) dbHelper.deleteUser(userName);
    }

    public void setValues(User user)
    {
        binding.edtName.setText(user.getName()+"");
        binding.edtAge.setText(user.getAge()+"");
        binding.edtLocation.setText(user.getLocation()+"");
    }
}
