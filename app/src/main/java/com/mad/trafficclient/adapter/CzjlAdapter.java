package com.mad.trafficclient.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mad.trafficclient.R;
import com.mad.trafficclient.bean.Czjl;

import java.util.List;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/9/29 at 9:57
 */
public class CzjlAdapter extends ArrayAdapter<Czjl> {

    public CzjlAdapter(@NonNull Context context, @NonNull List<Czjl> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.czjl_item, parent, false);
            holder = new ViewHolder();
            holder.itemXh = (TextView) convertView.findViewById(R.id.item_xh);
            holder.itemCh = (TextView) convertView.findViewById(R.id.item_ch);
            holder.itemName = (TextView) convertView.findViewById(R.id.item_name);
            holder.itemJe = (TextView) convertView.findViewById(R.id.item_je);
            holder.itemSj = (TextView) convertView.findViewById(R.id.item_sj);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Czjl czjl = getItem(position);
        holder.itemXh.setText(position + 1 + "");
        holder.itemCh.setText(czjl.getCh());
        holder.itemJe.setText(czjl.getJe() + "");
        holder.itemSj.setText(czjl.getTime());
        holder.itemName.setText(czjl.getName());
        return convertView;
    }


    static class ViewHolder {

        private TextView itemXh;
        private TextView itemCh;
        private TextView itemName;
        private TextView itemJe;
        private TextView itemSj;
    }

    private void initView() {
    }
}
