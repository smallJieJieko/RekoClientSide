package com.jieko.reko.Login;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;

import com.jieko.reko.PublicClass.SomeFunction;
import com.jieko.reko.R;
import com.jieko.reko.RekoActovity.HomeActivity;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class Login extends AppCompatActivity {

    private EditText accountEditText;
    private EditText passwordEditText;
    private CardView forget;
    private CardView login;
    private CardView register;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);//去除标题栏
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        accountEditText = findViewById(R.id.login_edit_account);
        passwordEditText = findViewById(R.id.login_edit_password);
        forget = findViewById(R.id.login_forget);
        login = findViewById(R.id.login_login);
        register = findViewById(R.id.login_register);

        forget.setOnClickListener(onClickListener);
        login.setOnClickListener(onClickListener);
        register.setOnClickListener(onClickListener);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.login_forget:
                    //忘记密码
                    break;
                case R.id.login_login:
                    //登陆
                    MyLogin myLogin = new MyLogin();
                    myLogin.execute(accountEditText.getText().toString(), passwordEditText.getText().toString());
                    break;

                case R.id.login_register:
                    Intent intent = new Intent(Login.this, Register.class);
                    startActivity(intent);
                    //注册
                    break;
                default:
                    break;
            }
        }
    };
    private class MyLogin extends AsyncTask<String, Integer, String> {
        // 方法1：onPreExecute（）
        // 作用：执行 线程任务前的操作
        @Override
        protected void onPreExecute() {
            //text.setText("加载中");
            // 执行前显示提示
        }

        // 方法2：doInBackground（）
        // 作用：接收输入参数、执行任务中的耗时操作、返回 线程任务执行的结果
        // 此处通过计算从而模拟“加载进度”的情况
        @Override
        protected String doInBackground(String... params) {



            String TAG = "Do in back";
            Log.d(TAG, "doInBackground: " + params[0]);
            Log.d(TAG, "doInBackground: " + params[1]);

            OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
            final String url = getString(R.string.ip)+"/api/userbase/check/" + params[0] + "," + params[1];
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            final Call call = okHttpClient.newCall(request);
            String result=null;
            try {
                Response response = call.execute();
                result=response.body().string();
                Log.d(TAG, "doInBackground: " + result);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }

        // 方法4：onPostExecute（）
        // 作用：接收线程任务执行结果、将执行结果显示到UI组件
        @Override
        protected void onPostExecute(String result) {
            // 执行完毕后，则更新UI
            if (result==null){
                String log = getResources().getString(R.string.please_input_account);
                SomeFunction.showToast(Login.this, log);
            }
            else if (result.equals("true")) {
                Intent intent = new Intent();
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                //设置不能再返回这个页面
                intent.setClass(Login.this, HomeActivity.class);
                startActivity(intent);
            } else {
                String log = getResources().getString(R.string.wrong_login);
                SomeFunction.showToast(Login.this, log);
            }
        }

        // 方法5：onCancelled()
        // 作用：将异步任务设置为：取消状态
        @Override
        protected void onCancelled() {
        }
    }
}
