package com.rcl.excalibur.adapters.guest;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import com.rcl.excalibur.R;
import com.rcl.excalibur.adapters.BaseAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;

public class SecurityQuestionsAdapter extends BaseAdapter<String, SecurityQuestionsAdapter.SecurityQuestionsHolder> {
    private int selectedPosition = -1;

    public SecurityQuestionsAdapter(Observer<String> observer) {
        super(observer);
    }

    @Override
    protected int getLayout() {
        return R.layout.item_securirty_questions;
    }

    @NonNull
    @Override
    protected SecurityQuestionsHolder getViewHolder(View view) {
        return new SecurityQuestionsHolder(view);
    }

    @Override
    public void onBindViewHolder(SecurityQuestionsHolder holder, int position) {
        holder.radioButton.setChecked(selectedPosition == position);
        holder.question.setText(items.get(position));
    }

    public int getSelectedPosition() {
        return selectedPosition;
    }


    class SecurityQuestionsHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.radio_question) RadioButton radioButton;
        @Bind(R.id.text_question) TextView question;

        SecurityQuestionsHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.radio_question)
        void itemChecked() {
            selectItem();
        }

        @OnClick(R.id.text_question)
        void itemTextChecked() {
            selectItem();
        }

        private void selectItem() {
            int buffer = selectedPosition;
            selectedPosition = getAdapterPosition();
            notifyItemChanged(buffer);
            notifyItemChanged(selectedPosition);
            onNext(question.getText().toString());
        }
    }
}
