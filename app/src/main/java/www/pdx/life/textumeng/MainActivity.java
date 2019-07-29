package www.pdx.life.textumeng;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.umeng.message.PushAgent;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Context mContext;
    private NotificationManager mNManager;
    private Notification notify1;
    Bitmap LargeBitmap = null;
    private static final int NOTIFYID_1 = 1;

    private Button btnNotificationShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //在所有的Activity 的onCreate 方法或在应用的BaseActivity的onCreate方法中添加以下方法
        PushAgent.getInstance(getApplicationContext()).onAppStart();
        componentInit();
        componentAddOnClickListener();

        mContext = MainActivity.this;
        //获得通知消息管理类对象
        mNManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    public void componentInit() {
        btnNotificationShow = findViewById(R.id.main_btnNotificationShow);
    }

    public void componentAddOnClickListener() {
        btnNotificationShow.setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_btnNotificationShow: {
                show();
                break;
            }
        }

    }

    public void show() {
        //Android8.0以上必须添加 渠道 才能显示通知栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //创建渠道
            String id = "my_channel_01";
            String name = "渠道名字";
            NotificationChannel mChannel = new NotificationChannel(id, name, NotificationManager.IMPORTANCE_HIGH);
            mNManager.createNotificationChannel(mChannel);
            //设置图片,通知标题,发送时间,提示方式等属性
            NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext, id);
            builder.setContentTitle("北极熊也有微笑")  //标题
                    .setContentText("北极熊是本人认为最棒的动物，没有之一")   //内容
                    .setSubText("--北极熊很Happy～")     //内容下面的一小段文字
                    .setTicker("收到北极熊也有微笑发来的的消息～")      //收到信息后状态栏显示的文字信息
                    .setWhen(System.currentTimeMillis())    //系统显示时间
                    .setSmallIcon(R.mipmap.ic_launcher)     //收到信息后状态栏显示的小图标
                    .setLargeIcon(LargeBitmap)//大图标
                    .setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE)    //设置默认的三色灯与振动器
                    .setDefaults(Notification.DEFAULT_SOUND)    //设置系统的提示音
                    .setAutoCancel(true);       //设置点击后取消Notification

            notify1 = builder.build();
            mNManager.notify(1, notify1);
        } else {
            //设置图片,通知标题,发送时间,提示方式等属性
            Notification.Builder builder = new Notification.Builder(mContext);
            builder.setContentTitle("北极熊也有微笑")  //标题
                    .setContentText("北极熊是本人认为最棒的动物，没有之一")   //内容
                    .setTicker("收到北极熊也有微笑发来的的消息～")      //收到信息后状态栏显示的文字信息
                    .setWhen(System.currentTimeMillis())    //系统显示时间
                    .setSmallIcon(R.mipmap.ic_launcher)     //收到信息后状态栏显示的小图标
                    .setLargeIcon(LargeBitmap)//大图标
                    .setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE)    //设置默认的三色灯与振动器
                    .setDefaults(Notification.DEFAULT_SOUND)    //设置系统的提示音
                    .setAutoCancel(true);       //设置点击后取消Notification

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                notify1 = builder.build();
            }
            mNManager.notify(1, notify1);
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
