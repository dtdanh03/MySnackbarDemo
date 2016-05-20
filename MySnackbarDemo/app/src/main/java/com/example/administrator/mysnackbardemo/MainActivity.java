package com.example.administrator.mysnackbardemo;

import android.app.ListActivity;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String[] items = { "iPhone 4", "iPhone 5", "iPhone 6",
            "Google Nexus 5" , "Google Nexus 6",
            "Samsung Galaxy S5", "Samsung Galaxy S6", "Samsung Galaxy S7"};
    Integer[] thumbnails = { R.drawable.iphone4, R.drawable.iphone5, R.drawable.iphone6,
                                R.drawable.nesus5, R.drawable.nesus6,
                                R.drawable.galaxys5, R.drawable.galaxys6, R.drawable.galaxys7};

    ArrayList<Phone> phoneList = new ArrayList<Phone>();
    Phone lastestDeletedPhone;
    int lastestDeletedIndex = 0;
    MyPhoneListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        populatePhoneList();
        ListView list = (ListView) findViewById(R.id.list);
        adapter = new MyPhoneListAdapter(this, R.layout.custom_row, phoneList);
        list.setAdapter(adapter);
    }

    private void populatePhoneList()
    {
        for (int i = 0; i < items.length; i++)
        {
            Phone temp = new Phone(items[i], thumbnails[i]);
            phoneList.add(temp);
        }
    }

    public void deletePhone(int pos)
    {
        Phone temp = phoneList.remove(pos);
        lastestDeletedPhone = temp;
        lastestDeletedIndex = pos;
        adapter.notifyDataSetChanged();

        Snackbar.make(findViewById(R.id.myCoordinatorLayout), "You just deleted a phone",
        Snackbar.LENGTH_LONG)
        .setAction("Undo", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                undoDelete();
            }
        })
        .show();
    }

    private void undoDelete()
    {
        if(lastestDeletedPhone != null)
        {
            phoneList.add(lastestDeletedIndex, lastestDeletedPhone);
            adapter.notifyDataSetChanged();
        }
    }
}
