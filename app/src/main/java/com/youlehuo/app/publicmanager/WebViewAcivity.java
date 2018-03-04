package com.youlehuo.app.publicmanager;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bartoszlipinski.recyclerviewheader2.RecyclerViewHeader;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.youlehuo.app.BaseActivity;
import com.youlehuo.app.R;
import com.yyl.multiview.RecyclerViewMultiHeader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by GetY on 2018/3/4.
 */

public class WebViewAcivity extends BaseActivity{
    @BindView(R.id.rl_web)
    RecyclerView recyclerView;
//    @BindView(R.id.rl_head)
//    RecyclerViewMultiHeader rl_head;
//    @BindView(R.id.web_out)
//    WebView web_out;

    boolean isFirst = true;
    private String[] array = {"fdsfsdfds","sdfsdfeww","sdfsdfeww","sdfsdfeww","sdfsdfeww","sdfsdfeww","sdfsdfeww","sdfsdfeww","sdfsdfeww","sdfsdfeww","sdfsdfeww","sdfsdfeww","sdfsdfeww","sdfsdfeww","sdfsdfeww","sdfsdfeww","sdfsdfeww","sdfsdfeww","egfgf","safdfs","weew","asdfdfd","ghfhgf","sdgdfgf","rrrr","gsfdg","sfgr","sfgfdgfdg","sfgfdgfdg","sfgfdgfdg","sfgfdgfdg","sfgfdgfdg","sfgfdgfdg","sfgfdgfdg","sfgfdgfdg","sfgfdgfdg","sfgfdgfdg","sfgfdgfdg","sfgfdgfdg","sfgfdgfdg","sfgfdgfdg","sfgfdgfdg"};
    @Override
    protected int setView() {
        return R.layout.webviewacivity;
    }

    @Override
    protected void initView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
//        rl_head.attachToWebView(recyclerView,web_out);
        List<String> strings = new ArrayList<>();
        for (int i = 0;i<array.length;i++){
            strings.add(array[i]);
        }
        WebHeadView webHeadView = new WebHeadView(context);
        WebAdapter adapter = new WebAdapter(context,R.layout.item_wev_recv,strings);
        adapter.addHeaderView(webHeadView);

        recyclerView.setAdapter(adapter);
        webHeadView.setUrl("");
    }

    @Override
    protected void initVariables() {

        //声明WebSettings子类
       /* WebSettings webSettings = web_out.getSettings();

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
        web_out.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        web_out.loadUrl("https://mbd.baidu.com/newspage/data/landingsuper?context=%7B%22nid%22%3A%22news_5109716774006913292%22%7D&n_type=1&p_from=4");
        web_out.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
//                if (isFirst){
//                    int height = (int) (view.getContentHeight() * view.getScale());
//                    final RelativeLayout.LayoutParams manager = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,height);
//                    rl_head.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            rl_head.setLayoutParams(manager);
//                        }
//                    });
//
////                    web_out.loadUrl("https://www.baidu.com");
////                    isFirst = false;
//                }
            }
        });*/

    }

    public class WebAdapter   extends BaseQuickAdapter<String, BaseViewHolder> {
        private Context mContext;

        public WebAdapter(Context context, @LayoutRes int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
            mContext = context;
        }
        @Override
        protected void convert(BaseViewHolder baseViewHolder, String s) {
            baseViewHolder.setText(R.id.tv_item, s);
        }
        /*@Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            RecyclerView.ViewHolder viewHolder;
            if (viewType == 0){
                View view = LayoutInflater.from(context).inflate(R.layout.item_wev_recv,null);
                view.requestFocus();
                viewHolder = new ItemViewHolder(view) ;
            }else {
                View view = LayoutInflater.from(context).inflate(R.layout.head_web,null);
                view.requestFocus();
                viewHolder = new WebViewHolder(view) ;
            }
            return viewHolder;
        }*/



      /*  @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

            if (holder instanceof ItemViewHolder ){
                ItemViewHolder itemHolder = (ItemViewHolder) holder;
                itemHolder.tv_item.setText(array[position-1]);
            }else {
                WebViewHolder itemHolder = (WebViewHolder) holder;
                //声明WebSettings子类
                WebSettings webSettings = itemHolder.web_rec.getSettings();

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
                itemHolder.web_rec.setWebViewClient(new WebViewClient(){
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        view.loadUrl(url);
                        return true;
                    }
                });
                itemHolder.web_rec.loadUrl("https://mbd.baidu.com/newspage/data/landingsuper?context=%7B%22nid%22%3A%22news_5109716774006913292%22%7D&n_type=1&p_from=4");

            }
        }

        @Override
        public int getItemCount() {
            return array.length+1;
        }

        @Override
        public int getItemViewType(int position) {
            if (position == 0){
                return 1;
            }else {
                return 0;
            }
        }

        class ItemViewHolder extends RecyclerView.ViewHolder{
            @BindView(R.id.tv_item)
            TextView tv_item;
            public ItemViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this,itemView);
            }
        }

        class WebViewHolder extends RecyclerView.ViewHolder{
            @BindView(R.id.web_rec)
            WebView web_rec;
            public WebViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this,itemView);
            }
        }
*/
    }
//http://p.codekk.com/detail/Android/mengzhidaren/RecylerViewMultiHeaderView

}
