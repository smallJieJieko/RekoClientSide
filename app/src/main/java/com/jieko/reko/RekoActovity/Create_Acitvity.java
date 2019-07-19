package com.jieko.reko.RekoActovity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.CursorLoader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

import com.jieko.reko.R;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class Create_Acitvity extends AppCompatActivity {

    ImageView uploadingPicture;
    CardView uploadingPictureClick;
    File pictureFile;
    String pictureFilePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);//去除标题栏
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crate__acitvity);

        //将文件或文件夹中文件复制到文件夹中，由于IO流是针对文件而言，所以没有权限访问文件目录
        //所以需要申请权限才能上传图片

        //先获取
        int permissionCheck = Math.abs(ContextCompat.checkSelfPermission(Create_Acitvity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE));
        if(Build.VERSION.SDK_INT>22){
            requestPermissions(new String[] {"android.permission.WRITE_EXTERNAL_STORAGE"}, permissionCheck);
        }
        uploadingPicture = findViewById(R.id.uploading_picture);
        uploadingPictureClick = findViewById(R.id.uploading_picture_button);

        uploadingPictureClick.setOnClickListener(onClickListener);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.uploading_picture_button:
                    //打开手机相册
                    Intent intent = new Intent(Intent.ACTION_PICK, null);
                    intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                    startActivityForResult(intent, 2);
                    //然后调取选择图片地址并开启异步操作
                    break;
                default:
                    break;
            }
        }
    };
    //处理上传图片
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == 2) {
            // 从相册返回的数据
            if (data != null) {
                // 得到图片的路径
                final Uri uri = data.getData();
                uploadingPicture.setImageURI(uri);
                //将uri路径转换为绝对路径
                String selectPhoto = getRealPathFromUri(this,uri);
                //赋值为全局变量
                pictureFilePath=selectPhoto;
//                pictureFile=new File(uri.getPath());
//                try {
//                    //先将uri格式的转为Bipmap 然后再转为Drawable
//                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
//                    BitmapDrawable bitmapDrawable= new BitmapDrawable(bitmap);
//                    uploadingPicture.setBackground(bitmapDrawable);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }

                UploadingPicture uploadingPicture = new UploadingPicture();
                uploadingPicture.execute();
            }
        }
    }
    public static String getRealPathFromUri(Context context, Uri uri) {
        if (context == null || uri == null) {
            return null;
        }
        if ("file".equalsIgnoreCase(uri.getScheme())) {
            return getRealPathFromUri_Byfile(context, uri);
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            return getRealPathFromUri_Api11To18(context, uri);
        }
//        int sdkVersion = Build.VERSION.SDK_INT;
//        if (sdkVersion < 11) {
//            // SDK < Api11
//            return getRealPathFromUri_BelowApi11(context, uri);
//        }
////        if (sdkVersion < 19) {
////             SDK > 11 && SDK < 19
////            return getRealPathFromUri_Api11To18(context, uri);
//            return getRealPathFromUri_ByXiaomi(context, uri);
////        }
//        // SDK > 19
        return getRealPathFromUri_AboveApi19(context, uri);//没用到
    }
    /**
     * 适配api19以上,根据uri获取图片的绝对路径
     */
    @SuppressLint("NewApi")
    private static String getRealPathFromUri_AboveApi19(Context context, Uri uri) {
        String filePath = null;
        String wholeID = null;

        wholeID = DocumentsContract.getDocumentId(uri);

        // 使用':'分割
        String id = wholeID.split(":")[1];

        String[] projection = { MediaStore.Images.Media.DATA };
        String selection = MediaStore.Images.Media._ID + "=?";
        String[] selectionArgs = { id };

        Cursor cursor = context.getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, projection,
                selection, selectionArgs, null);
        int columnIndex = cursor.getColumnIndex(projection[0]);

        if (cursor.moveToFirst()) {
            filePath = cursor.getString(columnIndex);
        }
        cursor.close();
        return filePath;
    }
    //针对图片URI格式为Uri:: file:///storage/emulated/0/DCIM/Camera/IMG_20170613_132837.jp
    private static String getRealPathFromUri_Byfile(Context context,Uri uri){
        String uri2Str = uri.toString();
        String filePath = uri2Str.substring(uri2Str.indexOf(":") + 3);
        return filePath;
    }
    /**
     * //适配api11-api18,根据uri获取图片的绝对路径。
     * 针对图片URI格式为Uri:: content://media/external/images/media/1028
     */
    private static String getRealPathFromUri_Api11To18(Context context, Uri uri) {
        String filePath = null;
        String[] projection = { MediaStore.Images.Media.DATA };

        CursorLoader loader = new CursorLoader(context, uri, projection, null,
                null, null);
        Cursor cursor = loader.loadInBackground();

        if (cursor != null) {
            cursor.moveToFirst();
            filePath = cursor.getString(cursor.getColumnIndex(projection[0]));
            cursor.close();
        }
        return filePath;
    }
    //异步上传图片
    private class UploadingPicture extends AsyncTask<String, Integer, String> {
        @Override
        protected String doInBackground(String... strings) {
            String TAG = "123";
            RequestBody requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("file", ".jpg", RequestBody.create(MediaType.parse("multipart/form-data"), new File(pictureFilePath)))
                    .build();
            OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
            String url = getString(R.string.ip)+"/api/userdetail/uploadImg";
            Request request = new Request.Builder()
                    .url(url)
                    .post(requestBody)
                    .build();
            final Call call = okHttpClient.newCall(request);
            String result = null;
            try {
                Response response = call.execute();
                result = response.body().string();
                Log.d(TAG, "545543240" + result);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
    //根据需求申请权限弹出界面
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                if (!(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED)) {
                    Toast.makeText(Create_Acitvity.this, "Permission denied to access your location.", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
