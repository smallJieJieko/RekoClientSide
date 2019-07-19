package com.jieko.reko.UserDetail;

import android.content.Intent;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import com.jieko.reko.R;
import com.jieko.reko.RekoActovity.Create_Acitvity;

public class UserDetail extends AppCompatActivity {
    ImageView portrait_view;
    CardView create_new_activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);//去除标题栏
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);
        //创建底部标题栏
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_activity:
                        break;
                    case R.id.action_message:
                        break;
                    case R.id.action_friends:
                        break;
                    case R.id.action_nearby:
                        break;
                }
                return true;
            }
        });
        create_new_activity = findViewById(R.id.create_new_activity);

        create_new_activity.setOnClickListener(onClickListener);
    }
    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.create_new_activity:
                    //顶端用户框
                    Intent intent = new Intent(UserDetail.this, Create_Acitvity.class);
                    startActivity(intent);
                    break;
                default:
                    break;
            }
        }
    };
}
