package com.example.administrator.mysnackbardemo;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 5/20/2016.
 */
public class MyPhoneListAdapter extends ArrayAdapter<Phone>
{
    Context context;
    ArrayList<Phone> phoneList;

    public MyPhoneListAdapter(Context context, int resource, ArrayList<Phone> objects) {
        super(context, resource, objects);
        this.context = context;
        this.phoneList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (convertView == null)
        {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            v = inflater.inflate(R.layout.custom_row, null);
        }

        ImageView imageView = (ImageView) v.findViewById(R.id.thumbnail);
        TextView textView = (TextView) v.findViewById(R.id.label);
        Button button = (Button) v.findViewById(R.id.button);

        Phone phone = phoneList.get(position);
        final int pos = position;
        textView.setText(phone.name);
        imageView.setImageResource(phone.thumnail);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity activity = (MainActivity) context;
                activity.deletePhone(pos);
            }
        });
        return v;
    }
}
