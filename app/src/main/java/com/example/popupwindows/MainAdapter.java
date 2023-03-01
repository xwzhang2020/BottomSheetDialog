package com.example.popupwindows;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.CustomViewHolder> {
        private final Context mContext;
        private List<ItemData> mItems;
        private OnItemClickListener onItemClickListener;
//        public void setSettingStore(SettingStore settingStore) {
//            this.settingStore = settingStore;
//        }

        MainAdapter(Context context, List<ItemData> settingStore) {
            mContext = context;
            this.mItems = settingStore;
        }

        @Override
        public int getItemCount() {
            return mItems.size();
        }


        @NonNull
        @Override
        public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_main, parent, false);
            return new CustomViewHolder(view);
        }


        @Override
        public void onBindViewHolder(@NonNull CustomViewHolder holder, @SuppressLint("RecyclerView") int position) {
            String str = mItems.get(position).getTitle();
            holder.tv_title.setText(str);
            holder.radio_button.setChecked(mItems.get(position).isChecked());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //dismiss();
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClickListener(v, position);
                    }
                }
            });
        }

        class CustomViewHolder extends RecyclerView.ViewHolder {
            private final TextView tv_title;
            private final RadioButton radio_button;
            CustomViewHolder(View itemView) {
                super(itemView);
                tv_title = (TextView) itemView.findViewById(R.id.tv_title);
                radio_button = (RadioButton) itemView.findViewById(R.id.radio_button);

            }
        }
        public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
            this.onItemClickListener = onItemClickListener;
        }

        public interface OnItemClickListener {
            void onItemClickListener(View item, int position);
        }

    }