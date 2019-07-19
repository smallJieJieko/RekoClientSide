package com.jieko.reko.Login;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.jieko.reko.PublicClass.SomeFunction;
import com.jieko.reko.R;
import com.jieko.reko.RekoActovity.HomeActivity;
import com.jieko.reko.model.UserBase;
import com.jieko.reko.model.UserDetail;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Register extends AppCompatActivity {

    private EditText accountEditText;
    private EditText passwordEditText;
    private EditText chechPasswordEditText;

    private CardView checkSecurityProtocol;
    private CardView checkLookSecurityProtocol;
    private CardView createNewUser;

    private ImageView checkProtocolImage;

    private final OkHttpClient client = new OkHttpClient();
    private Boolean checkflag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);//去除标题栏
        setContentView(R.layout.activity_register);

        accountEditText = findViewById(R.id.register_edit_account);
        passwordEditText = findViewById(R.id.register_edit_password);
        chechPasswordEditText = findViewById(R.id.register_edit_check_password);
        checkSecurityProtocol = findViewById(R.id.register_check_securityProtocol);
        checkLookSecurityProtocol = findViewById(R.id.register_check_look_securityProtocol);
        createNewUser = findViewById(R.id.register_createUser);

        checkProtocolImage = findViewById(R.id.check_protocol_image);

        checkLookSecurityProtocol.setOnClickListener(onClickListener);
        createNewUser.setOnClickListener(onClickListener);
        checkProtocolImage.setOnClickListener(onClickListener);
        checkSecurityProtocol.setOnClickListener(onClickListener);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.register_check_look_securityProtocol:
                    //查看安全说明
                    break;
                case R.id.check_protocol_image:
                    //勾选确认安全说明
                    if(checkflag==false){
                        checkProtocolImage.setBackgroundResource(R.drawable.check1_yes);
                        checkflag=true;
                    }
                    else {
                        checkProtocolImage.setBackgroundResource(R.drawable.check1_no);
                        checkflag=false;
                    }
                    break;
                case R.id.register_createUser:
                    if(checkflag==false)
                    {
                        String log = getResources().getString(R.string.protocol_check_falg);
                        SomeFunction.showToast(Register.this, log);
                        break;
                    }
                    //注册新用户并登陆
                    if (passwordEditText.getText().toString().equals(chechPasswordEditText.getText().toString())) {
                        //定义一个checkuBnumber用来检测用户编号是否重复
                        String checkuBnumber = "true";
                        //定义tempNumber用来生成用户编号
                        Long tempNumber = null;
                        //如果用户编号重复那么循环生成随机数直到不重复为止
                        while (checkuBnumber.equals("true")) {
                            tempNumber = generateRandomNumber(10);
                            String stringNumber = String.valueOf(tempNumber);
                            CreateUserNumber creatNewUser = new CreateUserNumber();
                            try {
                                //把生成的用户编号发到服务器端进行检测返回检测结果String
                                // doInBackground函数的返回值就是.get()
                                checkuBnumber = creatNewUser.execute(stringNumber).get();
                            } catch (ExecutionException e) {
                                e.printStackTrace();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        //把用户编号转换为String类型
                        String uBnumber = String.valueOf(tempNumber);
                        //创建新用户
                        MyRegister myRegister = new MyRegister();
                        myRegister.execute(uBnumber, accountEditText.getText().toString(), passwordEditText.getText().toString());
                        //创建用户详情
                    } else {
                        String log = getResources().getString(R.string.two_different_password);
                        SomeFunction.showToast(Register.this, log);
                    }
                    break;
                default:
                    break;
            }
        }
    };

    //注册用户
    private class MyRegister extends AsyncTask<String, Integer, String> {

        // 方法2：doInBackground（）
        // 作用：接收输入参数、执行任务中的耗时操作、返回 线程任务执行的结果
        // 此处通过计算从而模拟“加载进度”的情况
        @Override
        protected String doInBackground(String... params) {
            //new UserBase对象
            Long uBnumber = Long.parseLong(params[0]);
            UserBase userBase = new UserBase(uBnumber, params[1], params[2]);
            //把对象封装成json
            Gson gson = new Gson();
            String json = gson.toJson(userBase);
            RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8")
                    , json);
            //
            OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
            String url = getString(R.string.ip)+"/api/userbase";
            Request request = new Request.Builder()
                    .url(url)
                    .post(requestBody)
                    .build();
            final Call call = okHttpClient.newCall(request);
            String result = null;
            try {
                Response response = call.execute();
                result = response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }


            //new userDetail
            UserDetail userDetail=new UserDetail(uBnumber);
            //把对象封装成json
            Gson gson1 = new Gson();
            String json1 = gson1.toJson(userDetail);
            RequestBody requestBody1 = FormBody.create(MediaType.parse("application/json; charset=utf-8")
                    , json1);
            //
            OkHttpClient okHttpClient1 = new OkHttpClient.Builder().build();
            String url1 = getString(R.string.ip)+"/api/userdetail";
            Request request1 = new Request.Builder()
                    .url(url1)
                    .post(requestBody1)
                    .build();
            final Call call1 = okHttpClient1.newCall(request1);
            try {
                Response response1 = call1.execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }

        // 方法4：onPostExecute（）
        // 作用：接收线程任务执行结果、将执行结果显示到UI组件
        @Override
        protected void onPostExecute(String result) {
            if (result.equals("true")) {
                String log = getResources().getString(R.string.create_user_success);
                SomeFunction.showToast(Register.this, log);
                Intent intent = new Intent();
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                //设置不能再返回这个页面
                intent.setClass(Register.this, HomeActivity.class);
                startActivity(intent);
            } else {
                String log = getResources().getString(R.string.create_user_fail);
                SomeFunction.showToast(Register.this, log);
            }
        }
    }

    //生成用户编号
    private class CreateUserNumber extends AsyncTask<String, Integer, String> {
        // 方法2：doInBackground（）
        // 作用：接收输入参数、执行任务中的耗时操作、返回 线程任务执行的结果
        // 此处通过计算从而模拟“加载进度”的情况
        @Override
        protected String doInBackground(String... params) {
            OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
            final String url = "http://www.jieko.club:8080/api/userbase/ubnumber/" + params[0];
//            final String url = "http://10.0.2.2:8080/api/userbase/ubnumber/" + params[0];
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            final Call call = okHttpClient.newCall(request);
            String result = null;
            try {
                Response response = call.execute();
                result = response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }

        // 方法4：onPostExecute（）
        // 作用：接收线程任务执行结果、将执行结果显示到UI组件
        @Override
        protected void onPostExecute(String result) {

        }

        // 方法5：onCancelled()
        // 作用：将异步任务设置为：取消状态
        @Override
        protected void onCancelled() {
        }
    }

    //生成随机数
    protected long generateRandomNumber(int n) {
        if (n < 1) {
            throw new IllegalArgumentException("随机数位数必须大于0");
        }
        return (long) (Math.random() * 9 * Math.pow(10, n - 1)) + (long) Math.pow(10, n - 1);
    }
}
