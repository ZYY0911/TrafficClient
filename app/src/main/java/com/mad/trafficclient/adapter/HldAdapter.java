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
import com.mad.trafficclient.bean.Hld;

import java.util.List;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/9/29 at 8:56
 */
public class HldAdapter extends ArrayAdapter<Hld> {

    public HldAdapter(@NonNull Context context, @NonNull List<Hld> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.hld_item, parent, false);
            holder = new ViewHolder();
            holder.itemLk = (TextView) convertView.findViewById(R.id.item_lk);
            holder.itemRed = (TextView) convertView.findViewById(R.id.item_red);
            holder.itemGreen = (TextView) convertView.findViewById(R.id.item_green);
            holder.itemYellow = (TextView) convertView.findViewById(R.id.item_yellow);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Hld hld = getItem(position);
        holder.itemLk.setText(hld.getLk() + "");
        holder.itemRed.setText(hld.getRedTime() + "");
        holder.itemGreen.setText(hld.getGreenTime() + "");
        holder.itemYellow.setText(hld.getYellowTime() + "");
        return convertView;
    }

    static class ViewHolder {

        private TextView itemLk;
        private TextView itemRed;
        private TextView itemGreen;
        private TextView itemYellow;
    }

    private void initView() {

    }
}
