package de.hering.tdoo.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

import de.hering.tdoo.R;
import de.hering.tdoo.TodoDetailActivity;
import de.hering.tdoo.TodoDetailFragment;
import de.hering.tdoo.model.Todo;

/**
 * Created by Heringsfilet on 06.04.2016.
 */
public class TodoAdapter extends
    RecyclerView.Adapter<TodoAdapter.ViewHolder> {

    private ArrayList<Todo> mValues;

    public TodoAdapter(ArrayList<Todo> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.todo_list_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).name);
        holder.mDueDateView.setText(mValues.get(position).getDueDateString());

        // Todo set OnClickListener
        if(mValues.get(position).isFavourite){
            holder.mStarViewOn.setVisibility(View.VISIBLE);
            holder.mStarViewOff.setVisibility(View.GONE);
        }else{
            holder.mStarViewOn.setVisibility(View.GONE);
            holder.mStarViewOff.setVisibility(View.VISIBLE);
        }

        // Todo set OnClickListener
        holder.mCheckboxView.setChecked(mValues.get(position).isDone);

        // dueDate is in the past
        if(mValues.get(position).dueDate.before(new Date())){
            holder.mItemLayout.setBackgroundColor(Color.parseColor("#FFCDD2"));
        }

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, TodoDetailActivity.class);
                intent.putExtra(TodoDetailFragment.ARG_ITEM_ID, holder.mItem.name);

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final ImageView mStarViewOn;
        public final ImageView mStarViewOff;
        public final CheckBox mCheckboxView;
        public final TextView mDueDateView;
        public final LinearLayout mItemLayout;
        public Todo mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.name);
            mStarViewOn = (ImageView) view.findViewById(R.id.starViewOn);
            mStarViewOff = (ImageView) view.findViewById(R.id.starViewOff);
            mCheckboxView = (CheckBox) view.findViewById(R.id.checkBox);
            mDueDateView = (TextView) view.findViewById(R.id.duedate);
            mItemLayout = (LinearLayout) view.findViewById(R.id.list_item_background);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mIdView.getText() + "'";
        }
    }
}
