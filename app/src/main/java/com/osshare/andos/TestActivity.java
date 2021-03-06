package com.osshare.andos;

import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.osshare.andos.base.abs.AbsActivity;
import com.osshare.core.view.pull.temp.MaterialPullLayout;
import com.osshare.framework.base.BaseActivity;
import com.osshare.framework.base.BaseAdapter;
import com.osshare.framework.base.BaseViewHolder;

import org.taptwo.android.widget.ViewFlow;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * Created by apple on 16/12/6.
 */
public class TestActivity extends AbsActivity {
//    private PullXLayout pvContainer;
    private MaterialPullLayout pvContainer;

    private RecyclerView rvContent;
    private BaseAdapter<String> adapter;

    private TextView textView;
    private ProgressBar progressBar;
    private ImageView imageView;

    private TextView footerTextView;
    private ProgressBar footerProgressBar;
    private ImageView footerImageView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        immersiveHeaderContainer(R.id.layout_title_bar);

        ((TextView) findViewById(R.id.tv_title)).setText("测试");

        List<String> data = new ArrayList<>();
//        ReentrantLock
        for (int i = 0; i < 30; i++) {
            data.add("测试"+i);
        }


        pvContainer= (MaterialPullLayout) findViewById(R.id.pl_container);
        pvContainer.setVisibility(View.GONE);

        rvContent = (RecyclerView) findViewById(R.id.rv_content);
        rvContent.setLayoutManager(new LinearLayoutManager(TestActivity.this));
        adapter = new BaseAdapter<String>(TestActivity.this, data) {
            @Override
            public View getItemView(ViewGroup parent, int viewType) {
                return inflater.inflate(R.layout.item_test001, parent, false);
            }

            @Override
            public void onBindViewHolder(BaseViewHolder holder, int position) {
                String itemBean = getItem(position);
                TextView test=holder.getView(R.id.tv_btn);
                test.setText(itemBean);
            }
        };
        rvContent.setAdapter(adapter);
    }

    public void banner(){

    }




    private View createFooterView() {
        View footerView = LayoutInflater.from(pvContainer.getContext())
                .inflate(R.layout.layout_footer, null);
        footerProgressBar = (ProgressBar) footerView
                .findViewById(R.id.footer_pb_view);
        footerImageView = (ImageView) footerView
                .findViewById(R.id.footer_image_view);
        footerTextView = (TextView) footerView
                .findViewById(R.id.footer_text_view);
        footerProgressBar.setVisibility(View.GONE);
        footerImageView.setVisibility(View.VISIBLE);
        footerImageView.setImageResource(R.drawable.down_arrow);
        footerTextView.setText("上拉加载更多...");
        return footerView;
    }

    private View createHeaderView() {
        View headerView = LayoutInflater.from(pvContainer.getContext())
                .inflate(R.layout.layout_header, null);
        progressBar = (ProgressBar) headerView.findViewById(R.id.pb_view);
        textView = (TextView) headerView.findViewById(R.id.text_view);
        textView.setText("下拉刷新");
        imageView = (ImageView) headerView.findViewById(R.id.image_view);
        imageView.setVisibility(View.VISIBLE);
        imageView.setImageResource(R.drawable.down_arrow);
        progressBar.setVisibility(View.GONE);
        return headerView;
    }

}
