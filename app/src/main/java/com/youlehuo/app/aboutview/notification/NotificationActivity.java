package com.youlehuo.app.aboutview.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RemoteViews;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.youlehuo.app.BaseActivity;
import com.youlehuo.app.MainActivity;
import com.youlehuo.app.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;

/**
 * Created by dayima on 17-1-5.
 */

public class NotificationActivity extends BaseActivity {

    private NotificationManager nftManager;

    @Override
    protected int setView() {
        return R.layout.notificationactivity;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initVariables() {

    }

    public void normalClick(View view) {
        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(context, 11, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        Notification notification = builder
                .setContentTitle("这是普通标题")
                .setContentText("这是普通通知内容")
                .setSubText("普通通知应用描述")
                .setContentIntent(contentIntent)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.official)
                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setColor(0xFFFD671A)
                .setLargeIcon(BitmapFactory.decodeResource(
                        getResources(), R.drawable.capture02))
                .build();
        getNftManager().notify(1, notification);
    }

    public void bigTVClick(View view) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle();
        bigTextStyle.bigText("大文本展开通知文本主体");
        bigTextStyle.setBigContentTitle("大文本展开标题");
        bigTextStyle.setSummaryText("大文本展开应用描述");
        Notification notification = builder
                .setContentTitle("大文本收起标题")
                .setContentText("大文本收起内容")
                .setSubText("大文本收起应用描述")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.official)
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setDefaults(Notification.DEFAULT_ALL)
                .setStyle(bigTextStyle)
                .setColor(0xFFFD671A)
                .setLargeIcon(BitmapFactory.decodeResource(
                        getResources(), R.drawable.capture02))
                .build();
        getNftManager().notify(2, notification);
    }

    public void bigIVClick(View view) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        NotificationCompat.BigPictureStyle pictureStyle = new NotificationCompat.BigPictureStyle();
        pictureStyle.bigLargeIcon(BitmapFactory.decodeResource(
                getResources(), R.drawable.capture01));
        pictureStyle.bigPicture(BitmapFactory.decodeResource(
                getResources(), R.drawable.capture03));
        pictureStyle.setBigContentTitle("大图标题");
        pictureStyle.setSummaryText("大图应用描述");
        Notification notification = builder
                .setContentTitle("大图收起标题")
                .setContentText("大图收起内容")
                .setSubText("大图收起应用描述")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.official)
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setDefaults(Notification.DEFAULT_ALL)
                .setStyle(pictureStyle)
                .setColor(0xFFFD671A)
                .setLargeIcon(BitmapFactory.decodeResource(
                        getResources(), R.drawable.capture02))
                .build();
        getNftManager().notify(3, notification);
    }

    public void inBoxClick(View view) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
        inboxStyle.setBigContentTitle("inbox标题");
        inboxStyle.setSummaryText("inbox描述");
        inboxStyle.addLine("一行吧白鹭上青天");
        inboxStyle.addLine("桃花潭水深千尺");
        inboxStyle.addLine("啥都别说了，洗洗睡吧");
        Notification notification = builder
                .setContentTitle("inbox收起标题")
                .setContentText("inbox收起内容")
                .setSubText("inbox收起应用描述")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.official)
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setDefaults(Notification.DEFAULT_ALL)
                .setStyle(inboxStyle)
                .setColor(0xFFFD671A)
                .setLargeIcon(BitmapFactory.decodeResource(
                        getResources(), R.drawable.capture02))
                .build();
        getNftManager().notify(4, notification);
    }

    public void mainClick(View view) {
        RemoteViews rv = new RemoteViews(getPackageName(), R.layout.notice_layout);
        final RemoteViews rvs = new RemoteViews(getPackageName(), R.layout.notice_layouts);
//        BitmapFactory.decodeStream();

//        rvs.setImageViewBitmap(R.id.im_notice,);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        final Notification notification = builder
                .setContentTitle("自定义收起标题")
                .setContentText("自定义收起内容")
                .setSubText("自定义收应用描述")
                .setWhen(System.currentTimeMillis())
                .setCustomBigContentView(rvs)
                .setSmallIcon(R.drawable.official)
                .setDefaults(Notification.DEFAULT_ALL)
                .setColor(0xFFFD671A)
                .setLargeIcon(BitmapFactory.decodeResource(
                        getResources(), R.drawable.capture02))
                .build();
//        notification.contentView = rv;
        Glide.with(NotificationActivity.this)
                .load("http://a.hiphotos.baidu.com/image/pic/item/9e3df8dcd100baa145b341714e10b912c9fc2e58.jpg")
                .downloadOnly(new SimpleTarget<File>() {
                    @Override
                    public void onResourceReady(File file, GlideAnimation<? super File> glideAnimation) {
                        try {
                            Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(file));
                            rvs.setImageViewBitmap(R.id.im_notice, bitmap);
                            getNftManager().notify(5, notification);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    public NotificationManager getNftManager() {
        if (nftManager == null) {
            nftManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        }
        return nftManager;
    }

    /**
     * 判断背景色是否为深色进而适配文字颜色
     *
     * @param context
     * @return
     */
    public static boolean isDarkNotificationTheme(Context context) {
        return !isSimilarColor(Color.BLACK, getNotificationColor(context));
    }

    /**
     * 获取通知栏颜色
     *
     * @param context
     * @return
     */
    public static int getNotificationColor(Context context) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        Notification notification = builder.build();
        int layoutId = notification.contentView.getLayoutId();
        ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(context).inflate(layoutId, null, false);
        if (viewGroup.findViewById(android.R.id.title) != null) {
            return ((TextView) viewGroup.findViewById(android.R.id.title)).getCurrentTextColor();
        }
        return findColor(viewGroup);
    }

    private static boolean isSimilarColor(int baseColor, int color) {
        int simpleBaseColor = baseColor | 0xff000000;
        int simpleColor = color | 0xff000000;
        int baseRed = Color.red(simpleBaseColor) - Color.red(simpleColor);
        int baseGreen = Color.green(simpleBaseColor) - Color.green(simpleColor);
        int baseBlue = Color.blue(simpleBaseColor) - Color.blue(simpleColor);
        double value = Math.sqrt(baseRed * baseRed + baseGreen * baseGreen + baseBlue * baseBlue);
        if (value < 180.0) {
            return true;
        }
        return false;
    }

    private static int findColor(ViewGroup viewGroupSource) {
        int color = Color.TRANSPARENT;
        LinkedList<ViewGroup> viewGroups = new LinkedList<>();
        viewGroups.add(viewGroupSource);
        while (viewGroups.size() > 0) {
            ViewGroup viewGroup1 = viewGroups.getFirst();
            for (int i = 0; i < viewGroup1.getChildCount(); i++) {
                if (viewGroup1.getChildAt(i) instanceof ViewGroup) {
                    viewGroups.add((ViewGroup) viewGroup1.getChildAt(i));
                } else if (viewGroup1.getChildAt(i) instanceof TextView) {
                    if (((TextView) viewGroup1.getChildAt(i)).getCurrentTextColor() != -1) {
                        color = ((TextView) viewGroup1.getChildAt(i)).getCurrentTextColor();
                    }
                }
            }
            viewGroups.remove(viewGroup1);
        }
        return color;
    }
}
