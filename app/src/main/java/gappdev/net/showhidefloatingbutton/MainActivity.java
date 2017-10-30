package gappdev.net.showhidefloatingbutton;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import gappdev.net.showhidefloattingbutton.R;

public class MainActivity extends AppCompatActivity {
    private List<String> mList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private FloatingActionButton mActionButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpView();
        fakeData();
        setUpRecyclerView();
        showHideWhenScroll();
    }

    private void setUpView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mActionButton = (FloatingActionButton) findViewById(R.id.fab);
    }
    private void fakeData() {
        Random random = new Random();
        for (int i = 0; i < 100; i++)
            mList.add(String.valueOf(random.nextInt(100)));
    }
    private void setUpRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager
                .VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        ListAdapter listAdapter = new ListAdapter(mList);
        mRecyclerView.setAdapter(listAdapter);
    }

    private void showHideWhenScroll() {
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                //dy > 0: scroll up; dy < 0: scroll down
                if (dy > 0) mActionButton.hide();
                else mActionButton.show();
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }


}

class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private List<String> mList = new ArrayList<>();

    ListAdapter(List<String> list) {
        if (list != null) mList.addAll(list);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        ViewHolder(View v) {
            super(v);
            textView = (TextView) v.findViewById(R.id.text_view);
        }
    }

    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recycler_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListAdapter.ViewHolder holder, int position) {
        holder.textView.setText(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}


