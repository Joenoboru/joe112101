package com.example.user.joe112101;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by user on 2016/11/21.
 */

public class Myadapter extends BaseAdapter { //繼承baseadapter 要實作4個抽象方法

    Context context;
    String[] data; //將fruits[]資料傳到 data中
    boolean[] chks;//紀錄checkbox資訊的 布林陣列

    public Myadapter(Context context, String[] d) //用建構將mainActivity方法 與 fruits內容 傳至Myadapter
    {
        this.context = context;//this.context是 上面的context ,context是 MainActivity方法;
        this.data = d; //用data[]陣列 接收 fruits[]內容
        chks = new boolean[d.length];//chks長度將與data[]相同
    }
    @Override
    public int getCount() {
        return data.length;//listview的列數
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position,  View convertView, ViewGroup parent) {//listview的內容,若getCount為10為position 0~9
        //LayoutInflater inflater = getLayoutInflater(); 利用inflater 抓到自訂的listview格式(myitem)
                                  //getLayoutInflater是MainActivity的方法 在此類別無法使用
        Log.d("LV", "getView, position:" + position + ", content:" + data[position]);
        /*手機畫面僅會建立畫面中會顯示的getView,畫面外的將不會建立, 在畫面捲動到其他位置的當下才建立資料,因此會造成
          原本checkbox已打勾,捲動畫面後,上面的checkbox超出畫面 被丟到垃圾桶,捲回來時 打勾消失。
          要解決此問題便需要建立一個陣列(ex.chks[])紀錄哪一個count被打勾,再捲回建立時便能夠將打勾資訊一併建立
        */

        ViewHolder holder; //宣告一ViewHolder變數存放捲動而被丟置資源回收桶的資料
        if(convertView==null)
        {
            //用LayoutInflater.from(context)接收MainActivity 傳過來的Inflater方法
            LayoutInflater inflater = LayoutInflater.from(context);
            //View v = inflater.inflate(R.layout.myitem,null);
            convertView = inflater.inflate(R.layout.myitem, null);
            holder = new ViewHolder();
            //TextView tv = (TextView) v.findViewById(R.id.textView);//將myitem格式與TextView建立關聯
            holder.tv = (TextView) convertView.findViewById(R.id.textView);
            //Button btn = (Button) v.findViewById(R.id.button);
            holder.btn = (Button) convertView.findViewById(R.id.button);
            //CheckBox checkBox = (CheckBox) v.findViewById(R.id.checkBox);
            holder.checkBox = (CheckBox) convertView.findViewById(R.id.checkBox);
            convertView.setTag(holder);

        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tv.setText(data[position]);
        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, data[position], Toast.LENGTH_SHORT).show();
            }
        });
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                chks[position] = isChecked;//當checkbox點擊後,改變chks[]的打勾資訊
            }
        });
        holder.checkBox.setChecked(chks[position]); //將checkbox打勾資訊記錄在chks[]中,使畫面捲動建立getview時能一併將打勾資訊建立
        //return v;
        return convertView;
    }
    static class ViewHolder
    {
        TextView tv;
        Button btn;
        CheckBox checkBox;
    }
}
