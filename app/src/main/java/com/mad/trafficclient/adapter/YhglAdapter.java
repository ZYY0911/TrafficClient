package com.mad.trafficclient.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mad.trafficclient.R;
import com.mad.trafficclient.bean.Yhzx;
import com.mad.trafficclient.util.MyHorizontalScrollView;

import java.util.List;

/**
 * @LogIn Name zhangyingyu
 * @Create by 张瀛煜 on 2020-09-29 at 18:01
 */
public class YhglAdapter extends ArrayAdapter<Yhzx> {
    private int width;
    private boolean isShow = true;

    public interface OnMyitemClick {
        void myItemClick(int position, int lx);
    }

    private OnMyitemClick myitemClick;

    public void setMyitemClick(OnMyitemClick myitemClick) {
        this.myitemClick = myitemClick;
    }

    public YhglAdapter(@NonNull Context context, @NonNull List<Yhzx> objects, int width) {
        super(context, 0, objects);
        this.width = width;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.yhzx_item, parent, false);
            holder = new ViewHolder();
            holder.scrollViewasdfg = (MyHorizontalScrollView) convertView.findViewById(R.id.scroll_viewasdfg);
            holder.layout = (LinearLayout) convertView.findViewById(R.id.layout);
            holder.itemPhoto = (ImageView) convertView.findViewById(R.id.item_photo);
            holder.itemType = (TextView) convertView.findViewById(R.id.item_type);
            holder.itemName = (TextView) convertView.findViewById(R.id.item_name);
            holder.itemSex = (TextView) convertView.findViewById(R.id.item_sex);
            holder.itemTel = (TextView) convertView.findViewById(R.id.item_tel);
            holder.btDetails = (Button) convertView.findViewById(R.id.bt_details);
            holder.tvSc = (TextView) convertView.findViewById(R.id.tv_sc);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final Yhzx yhzx = getItem(position);
        holder.itemType.setText(getRoleTyep(yhzx.getUserRole()));
        holder.itemName.setText("用户名：" + yhzx.getUsername());
        holder.itemSex.setText("性别：" + yhzx.getPsex());
        holder.itemPhoto.setImageResource(yhzx.getPsex().equals("男") ? R.drawable.touxiang_2 : R.drawable.touxiang_1);
        holder.itemTel.setText("电话：" + yhzx.getPtel());
        holder.tvSc.setText(yhzx.isSc() ? "已收藏" : "收藏");
        holder.scrollViewasdfg.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (yhzx.isIs()) {
                    holder.scrollViewasdfg.fullScroll(View.FOCUS_RIGHT);
                } else {
                    holder.scrollViewasdfg.fullScroll(View.FOCUS_LEFT);
                }
            }
        }, 300);
        holder.tvSc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.tvSc.getText().toString().equals("收藏")) {
                    isShow = false;
                    myitemClick.myItemClick(position, 2);
                }
            }
        });
        holder.btDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isShow = false;
                myitemClick.myItemClick(position, 3);
            }
        });
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width, ViewGroup.LayoutParams.MATCH_PARENT);
        holder.layout.setLayoutParams(layoutParams);
        holder.scrollViewasdfg.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
               switch (motionEvent.getAction()){
                   case MotionEvent.ACTION_DOWN:
                       x = (int) motionEvent.getX();
                       break;
                   case MotionEvent.ACTION_MOVE:
                       int end = (int) motionEvent.getX();
                       if (x-end>0){
                           myitemClick.myItemClick(position, 1);
                       }
                       break;
               }
                return false;
            }
        });
        return convertView;
    }
    private int x;

    public void setShow(boolean show) {
        isShow = show;
    }

    private String getRoleTyep(String tyep) {
        switch (tyep) {
            case "RO1":
                return "普通用户";
            case "RO2":
                return "一般管理员";
            default:
                return "超级管理员";
        }
    }

    static class ViewHolder {
        private MyHorizontalScrollView scrollViewasdfg;
        private LinearLayout layout;
        private ImageView itemPhoto;
        private TextView itemType;
        private TextView itemName;
        private TextView itemSex;
        private TextView itemTel;
        private Button btDetails;
        private TextView tvSc;

    }

    private void initView() {


    }
}
