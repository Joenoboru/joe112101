package com.example.user.joe112101;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    String fruits[] = {"蘋果","柳丁","葡萄","鳳梨","蘋果1","柳丁1","葡萄1","鳳梨1","蘋果2","柳丁2","葡萄2","鳳梨2"};
    Myadapter adapter; //將adapter宣告在外面 以讓其他方法能夠使用
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv=(ListView) findViewById(R.id.listView);
        adapter = new Myadapter(MainActivity.this, fruits);
        lv.setAdapter(adapter);
    }
    //建立右上角的optionmenu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("Show!");
        return super.onCreateOptionsMenu(menu);
    }
    //用stringbuilder儲存被選取的fruits[]內容
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        StringBuilder sb = new StringBuilder();
        for (int i=0;i<fruits.length;i++)
        {
            if (adapter.chks[i])
            {
                sb.append(fruits[i] + ",");
            }
        }
        Toast.makeText(MainActivity.this, sb.toString(), Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
    }

}
