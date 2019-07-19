package com.jieko.reko.RekoActovity;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.jieko.reko.R;
import com.jieko.reko.UserDetail.UserDetail;

public class HomeActivity extends AppCompatActivity {

    ImageView topAccountBox;
    ImageView topStarts;
    ImageView topTimeLine;
    ImageView tempImage;

    private Boolean checkflag = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);//去除标题栏
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        topAccountBox=findViewById(R.id.home_top_account_box);
        topStarts=findViewById(R.id.home_top_starts);
        topTimeLine=findViewById(R.id.home_top_timeline);

        tempImage=findViewById(R.id.home_temp_image);

        topAccountBox.setOnClickListener(onClickListener);
        topStarts.setOnClickListener(onClickListener);
        topTimeLine.setOnClickListener(onClickListener);
    }
    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.home_top_account_box:
                    //顶端用户框
                    Intent intent = new Intent(HomeActivity.this, UserDetail.class);
                    startActivity(intent);
                    break;
                case R.id.home_top_starts:
                    //顶端关注框
                    break;
                case R.id.home_top_timeline:
                    //顶端时间线
                    if(checkflag==false){
                        tempImage.setBackgroundResource(R.drawable.temp_timeline);
                        checkflag=true;
                    }
                    else {
                        tempImage.setBackgroundResource(R.drawable.temp_home);
                        checkflag=false;
                    }
                    break;
                default:
                    break;
            }
        }
    };
}
