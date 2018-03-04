package com.youlehuo.app.publicmanager.newfirst;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.youlehuo.app.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author ChayChan
 * @description: 新闻详情页头部
 * @date 2017/6/28  15:25
 */

public class NewsDetailHeaderView extends FrameLayout {

    private static final String NICK = "chaychan";

    @BindView(R.id.tvTitle)
    TextView mTvTitle;

    @BindView(R.id.ll_info)
    public LinearLayout mLlInfo;

    @BindView(R.id.iv_avatar)
    ImageView mIvAvatar;

    @BindView(R.id.tv_author)
    TextView mTvAuthor;

    @BindView(R.id.tv_time)
    TextView mTvTime;

    @BindView(R.id.wv_content)
    WebView mWvContent;

    private Context mContext;

    public NewsDetailHeaderView(Context context) {
        this(context, null);
    }

    public NewsDetailHeaderView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NewsDetailHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView();
    }

    private void initView() {
        inflate(getContext(), R.layout.header_news_detail, this);
        ButterKnife.bind(this, this);
    }

    public void setDetail(Object detail,LoadWebListener listener) {
        mWebListener = listener;

        mTvTitle.setText("标题");

        if (detail == null) {
            //如果没有用户信息
//            mLlInfo.setVisibility(GONE);
        } else {
//            if (!TextUtils.isEmpty(detail.media_user.avatar_url)){
//                GlideUtils.loadRound(mContext, detail.media_user.avatar_url, mIvAvatar);
//            }
//            mTvAuthor.setText(detail.media_user.screen_name);
//            mTvTime.setText(com.chaychan.news.utils.DateUtils.getShortTime(detail.publish_time * 1000L));
        }

//        if (TextUtils.isEmpty(""))
//            mWvContent.setVisibility(GONE);
        WebSettings webSettings = mWvContent.getSettings();

//如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
        webSettings.setJavaScriptEnabled(true);

//支持插件

//设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小

//缩放操作
        webSettings.setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
        webSettings.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
        webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件

//其他细节操作
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //关闭webview中缓存
        webSettings.setAllowFileAccess(true); //设置可以访问文件
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
        webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式
        mWvContent.getSettings().setJavaScriptEnabled(true);//设置JS可用
        mWvContent.addJavascriptInterface(new ShowPicRelation(mContext),NICK);//绑定JS和Java的联系类，以及使用到的昵称

        String htmlPart1 = "<!DOCTYPE HTML html>\n" +
                "<head><meta charset=\"utf-8\"/>\n" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, minimum-scale=1.0, user-scalable=no\"/>\n" +
                "</head>\n" +
                "<body>\n" +
                "<style> \n" +
                "img{width:100%!important;height:auto!important}\n" +
                " </style>";
        String htmlPart2 = "</body></html>";

        String html = htmlPart1 + "<p>好消息来了！地铁9号线一期将和杭海城铁无缝对接！ </p><p>2018年是杭州地铁的建设大年，日前，杭州公布了2018年的9个地铁开工项目，涉及1号线三期、3号线一期、4号线二期、5号线二期、6号线二期、7号线、8号线一期、9号线一期和10号线一期等，都将在2022年亚运会之前建成。 </p><p>目前，除了5号线一期、6号线一期在建之外，上述9条地铁线路今年都将开工建设，9号线是三期规划里进度最快的，而其他线路春节前也都已经获得了“初步设计”批复，目前大部分都处于开建前期准备工作。而这些线路，至2021年底，将全部建成通车。 </p><p>其中，地铁9号线一期将和备受关注的“杭海城铁”无缝连接！具体线路站点规划都出来了！ </p><p><strong>9号线一期具体有哪些站点呢？</strong></p><img src=\"http://p1.pstatp.com/large/65be00097e387e8a50e3\" img_width=\"570\" img_height=\"295\" inline=\"0\" alt=\"地铁9号线与杭海城铁无缝连接 站点规划出炉\" onerror=\"javascript:errorimg.call(this);\"><p><strong>9号线</strong></p><p><strong>四季青站—昌达路站</strong></p><p>地铁9号线一期工程利用既有1号线临平支线(客运中心站～临平站)向南北两端延伸，全长29.476公里，设21座车站。 </p><p>其中南段起于四季青站，终于既有客运中心站，新建线路10.83公里，设车站10座；北段起于既有临平站，终于昌达路站，新建线路6.109公里，设车站4座。建成后将是杭州南北向的一条重要交通路线。 </p><p><strong>9号线一期站点</strong></p><img src=\"http://p1.pstatp.com/large/65b80016946a7cc20c1a\" img_width=\"554\" img_height=\"418\" inline=\"0\" alt=\"地铁9号线与杭海城铁无缝连接 站点规划出炉\" onerror=\"javascript:errorimg.call(this);\"><img src=\"http://p1.pstatp.com/large/65bd000ab8483822c7aa\" img_width=\"360\" img_height=\"485\" inline=\"0\" alt=\"地铁9号线与杭海城铁无缝连接 站点规划出炉\" onerror=\"javascript:errorimg.call(this);\"><p>此外，根据杭州市政府现有规划，9号线将来会于杭州地铁1号线、2号线、3号线、4号线、6号线、7号线以及8号线设置换乘站。 </p><p>而那些担心从杭海城际铁路换乘地铁要走许多路的小伙伴也不必担心，杭州至海宁城际铁路与杭州地铁线将建成不出站，“零换乘”。 </p><p><strong>以后去海宁玩，可以这样坐车了</strong></p><p><strong>海宁皮革城</strong></p><p>喜欢去海宁皮革城买买买的临平小伙伴注意了，以后只要坐杭海城铁就可以到达了！ </p><p><strong>海宁潮</strong></p><p>喜欢欣赏美景的小伙伴，可以从临平出发，去海宁一睹盐官潮，那也是分分钟的事情啦！ </p><p>为杭海城铁早日建成加油吧~ </p><p>（临平生活圈综合大潮网、钱江晚报等） </p>" + htmlPart2;
    mWvContent.setWebViewClient(new WebViewClient(){
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    });
        mWvContent.loadUrl("https://mbd.baidu.com/newspage/data/landingsuper?context=%7B%22nid%22%3A%22news_5109716774006913292%22%7D&n_type=1&p_from=4");

//        mWvContent.loadDataWithBaseURL(null, html, "text/html", "UTF-8", null);
        mWvContent.setWebViewClient(new WebViewClient(){
        @Override
        public void onPageFinished(WebView view, String url) {
            addJs(view);//添加多JS代码，为图片绑定点击函数
            if (mWebListener != null){
                mWebListener.onLoadFinished();
            }
        }
    });
    }

    /**添加JS代码，获取所有图片的链接以及为图片设置点击事件*/
    private void addJs(WebView wv) {
        wv.loadUrl("javascript:(function  pic(){"+
                "var imgList = \"\";"+
                "var imgs = document.getElementsByTagName(\"img\");"+
                "for(var i=0;i<imgs.length;i++){"+
                "var img = imgs[i];"+
                "imgList = imgList + img.src +\";\";"+
                "img.onclick = function(){"+
                "window.chaychan.openImg(this.src);"+
                "}"+
                "}"+
                "window.chaychan.getImgArray(imgList);"+
                "})()");
    }

    private LoadWebListener mWebListener;

    /**页面加载的回调*/
    public interface LoadWebListener{
       void onLoadFinished();
    }
}
