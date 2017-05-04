package com.youlehuo.app.aboutview.recycleview.citylist;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.github.promeg.pinyinhelper.Pinyin;
import com.youlehuo.app.BaseActivity;
import com.youlehuo.app.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;

/**
 * Created by dayima on 17-1-11.
 */

public class CityActivity extends BaseActivity implements MySlideView.onTouchListener, CityAdapter.ItemClickListener {
    @BindView(R.id.my_slide_view)
    MySlideView my_slide_view;
    @BindView(R.id.rv_sticky_example)
    RecyclerView rv_sticky_example;
    //    @BindView(R.id.my_circle_view)
//    CircleTextView my_circle_view;
    @BindView(R.id.my_circle_view)
    TextView my_circle_view;
    private List<String> list;
    private List<CityBean> nameList;
    private LinearLayoutManager layoutManager;

    @Override
    protected int setView() {
        return R.layout.cityactivity;
    }

    public static String[] stringCitys = new String[]{
            "合肥", "张家界", "宿州", "淮北", "阜阳", "蚌埠", "淮南", "滁州",
            "马鞍山", "巢湖", "盐城", "扬州", "泰州", "饿了么",
            "徐州", "连云港", "宿迁", "姜堰", "泰兴", "靖江", "福州", "淮安",
            "如阳", "如果", "科比", "韦德", "诺维斯基", "麦迪", "乔丹", "姚明",
            "兴化", "南平", "三明", "芜湖", "铜陵", "安庆", "安阳", "黄山", "六安",
            "南通", "吴江", "如皋", "通州", "苏州", "江阴", "宜兴",
            "邳州", "新沂", "金坛", "溧阳", "常熟", "张家港", "太仓",
            "东台", "高邮", "仪征", "江都", "扬中", "匹夫", "旗舰", "启航",
            "镇江", "常州", "无锡", "昆山", "海门", "启东", "大丰",
            "池州", "宣城", "亳州", "明光", "天长", "桐城", "宁国",
            "复兴", "高领", "共兴", "柯家寨", "匹克", "句容", "丹阳"
    };

    /**
     * list.add("A");list.add("B");list.add("C");list.add("D");list.add("E");
     * list.add("F");list.add("G");list.add("H");list.add("I");list.add("J");
     * list.add("K");list.add("L");list.add("M");list.add("N");
     * list.add("O");list.add("P");list.add("Q");list.add("R");
     * list.add("S");list.add("T");list.add("U");list.add("V");
     * list.add("W");list.add("X");list.add("Y");list.add("Z");
     */
    @Override
    protected void initView() {
        list = new ArrayList<>();
        nameList = new ArrayList<>();
        for (int i = 0; i < stringCitys.length; i++) {
            CityBean cityBean = new CityBean();
            String value = stringCitys[i];
            cityBean.setCityName(value);

            char c = value.charAt(0);
            String pinYin = Pinyin.toPinyin(c);
            cityBean.setCityPinYin(pinYin);

            String name = pinYin.substring(0, 1);
            cityBean.setCityFist(name);
            if (!list.contains(name)) {
                list.add(name);
            }
            nameList.add(cityBean);
        }
        //自然序排序
        Collections.sort(list);
        Collections.sort(nameList, new CityComparator());


        my_slide_view.setHeightItemCount(list, this);
        CityAdapter cityAdapter = new CityAdapter(context, this);
        layoutManager = new LinearLayoutManager(context);
        rv_sticky_example.setLayoutManager(layoutManager);
        rv_sticky_example.addItemDecoration(new CityItemDecoration(context,nameList));
        rv_sticky_example.setAdapter(cityAdapter);
        cityAdapter.setData(nameList);
    }

    @Override
    protected void initVariables() {

    }

    @Override
    public void showTextView(String textView, boolean dismiss) {
        my_circle_view.setText(textView);
        if (!dismiss) {
            my_circle_view.setVisibility(View.VISIBLE);
        } else {
            my_circle_view.setVisibility(View.GONE);
        }
        int selectPosition = 0;
        for (int i = 0;i<nameList.size();i++){
            if (TextUtils.equals(textView,nameList.get(i).getCityFist())){
                selectPosition = i;
                break;
            }
        }
        selectPosition(selectPosition);
    }
    private class CityComparator implements Comparator<CityBean> {
        @Override
        public int compare(CityBean o1, CityBean o2) {
            return o1.getCityPinYin().compareTo(o2.getCityPinYin());
        }
    }

    @Override
    public void itemClick(int position) {
        Toast.makeText(context,nameList.get(position).getCityName(),Toast.LENGTH_SHORT).show();
    }

    private void selectPosition(int position){
        int firstPosition = layoutManager.findFirstVisibleItemPosition();
        int lastPosition = layoutManager.findLastVisibleItemPosition();
        if (position <= firstPosition) {
            rv_sticky_example.scrollToPosition(position);
        } else if (position <= lastPosition) {
            int top = rv_sticky_example.getChildAt(position - firstPosition).getTop();
            rv_sticky_example.scrollBy(0, top);
        } else {
            rv_sticky_example.scrollToPosition(position);
        }
    }
}
