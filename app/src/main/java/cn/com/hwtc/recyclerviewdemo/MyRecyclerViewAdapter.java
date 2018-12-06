package cn.com.hwtc.recyclerviewdemo;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * user:Created by jid on 2018/12/6 14:42:43.
 * email:18571762595@163.com.
 */
class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {
    private List<String> mList;
    private OnItemClickCallback mOnItemClickCallback;
    private OnItemLongClickCallback mOnItemLongClickCallback;

    public MyRecyclerViewAdapter(List<String> list) {
        this.mList = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_normal, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        if (null == mList)
            viewHolder.tvItem.setText("");
        else
            viewHolder.tvItem.setText(mList.get(i));

        int adapterPosition = viewHolder.getAdapterPosition();
        viewHolder.itemView.setOnClickListener(new ItemClickListener(adapterPosition, mList.get(adapterPosition)));
        viewHolder.itemView.setOnLongClickListener(new ItemLongClickListener(adapterPosition, mList.get(adapterPosition)));
    }

    @Override
    public int getItemCount() {
        if (null == mList) return 0;
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvItem;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItem = itemView.findViewById(R.id.tv_item);
        }
    }

    public interface OnItemClickCallback {
        void onItemClick(View view, int position, String itemString);
    }

    public interface OnItemLongClickCallback {
        void onItemLongClick(View view, int position, String itemString);
    }

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.mOnItemClickCallback = onItemClickCallback;
    }

    public void setOnItemLongClickCallback(OnItemLongClickCallback onItemLongClickCallback) {
        this.mOnItemLongClickCallback = onItemLongClickCallback;
    }

    private class ItemClickListener implements View.OnClickListener {
        int position;
        String itemString;

        private ItemClickListener(int adapterPosition, String itemString) {
            this.position = adapterPosition;
            this.itemString = itemString;
        }

        @Override
        public void onClick(View v) {
            if (null != mOnItemClickCallback)
                mOnItemClickCallback.onItemClick(v, position, itemString);
        }
    }

    private class ItemLongClickListener implements View.OnLongClickListener {
        int position;
        String itemString;

        private ItemLongClickListener(int adapterPosition, String itemString) {
            this.position = adapterPosition;
            this.itemString = itemString;
        }

        @Override
        public boolean onLongClick(View v) {
            if (null != mOnItemLongClickCallback)
                mOnItemLongClickCallback.onItemLongClick(v, position, itemString);
            return true; //这里需要return true,表示该长按时间被自己消费
        }
    }
}
