package com.gx303.alllogin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/5/12.
 */
public class qqlogin_account_listview_adapter extends BaseAdapter {
    private List<String> qqhaos;
    private LayoutInflater mInflater;
    public qqlogin_account_listview_adapter(Context context)
    {
        mInflater=LayoutInflater.from(context);
        qqhaos=new ArrayList<String>();
        qqhaos.add("15454545");
        qqhaos.add("444444411111");
    }
    @Override
    public int getCount() {
        return qqhaos.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView=mInflater.inflate(R.layout.qq_account_lv_item,null);
        TextView tv1=(TextView)convertView.findViewById(R.id.tv1);
        tv1.setText(qqhaos.get(position));
        return convertView;
    }
}
