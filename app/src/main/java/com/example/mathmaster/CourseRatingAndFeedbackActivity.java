package com.example.mathmaster;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mathmaster.databinding.ActivityCourseRatingAndFeedbackBinding;
import com.example.mathmaster.databinding.LayoutUserRatingFeedbackListItemBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class CourseRatingAndFeedbackActivity extends AppCompatActivity {

    private ActivityCourseRatingAndFeedbackBinding mBinding;
    private NoteDao mNoteDao;
    private ArrayList<NoteEntity> mNoteEntityList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_course_rating_and_feedback);

        mNoteDao = NoteRoomDatabase.getDatabase(getApplication()).noteDao();
        mBinding.layoutMain.setVisibility(View.VISIBLE);
        setUpToolbar();
        handleIntent();
        getList();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    private void setUpToolbar() {
        Objects.requireNonNull(getSupportActionBar()).setTitle("Streak Board");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, CourseRatingAndFeedbackActivity.class);
        return intent;
    }

    /*Handle intent*/
    private void handleIntent() {
        if (getIntent() != null) {
        }
    }

    private void initializeRecyclerView(ArrayList<NoteEntity> list) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getBaseContext(), RecyclerView.VERTICAL, false);
        mBinding.recyclerViewRatings.setLayoutManager(layoutManager);
        mBinding.recyclerViewRatings.setAdapter(new RatingsAndReviewsAdapter(list));
    }


    private void getNotes() {
        new GetNotesAsyncTask(mNoteDao).execute();
    }

    private class GetNotesAsyncTask extends AsyncTask<Void, Void, Void> {

        NoteDao mDao;

        public GetNotesAsyncTask(NoteDao noteDao) {
            mDao = noteDao;
        }


        @Override
        protected Void doInBackground(Void... voids) {

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (mNoteEntityList != null && !mNoteEntityList.isEmpty()) {
                initializeRecyclerView(mNoteEntityList);
            }
        }
    }

    @SuppressLint("CheckResult")
    private void getList() {

        Observable.create(new ObservableOnSubscribe<ArrayList<NoteEntity>>() {
            @Override
            public void subscribe(@io.reactivex.annotations.NonNull ObservableEmitter<ArrayList<NoteEntity>> e) throws Exception {

                List<NoteEntity> list = mNoteDao.getAllNotes();
                if (list != null && !list.isEmpty()) {
                    ArrayList<NoteEntity> entities = new ArrayList<>(list);
                    e.onNext(entities);
                } else {
                    e.onError(new Exception());
                }

            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<ArrayList<NoteEntity>>() {
                    @Override
                    public void accept(ArrayList<NoteEntity> noteEntities) throws Exception {
                        mNoteEntityList = noteEntities;
                        initializeRecyclerView(mNoteEntityList);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(getBaseContext(), "Error", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void noResultFound() {
        mBinding.layoutMain.setVisibility(View.GONE);
        mBinding.progressBar.setVisibility(View.GONE);
        mBinding.layoutRetry.setVisibility(View.GONE);

        mBinding.layoutNoResult.setVisibility(View.VISIBLE);
        mBinding.textViewNoResult.setText("NoRatingFeedbackFound");
    }


    private class RatingsAndReviewsAdapter extends RecyclerView.Adapter<RatingsAndReviewsAdapter.ViewHolder> {
        private ArrayList<NoteEntity> mList;

        RatingsAndReviewsAdapter(ArrayList<NoteEntity> list) {
            mList = list;
        }

        @NonNull
        @Override
        public RatingsAndReviewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutUserRatingFeedbackListItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.layout_user_rating_feedback_list_item, parent, false);
            return new RatingsAndReviewsAdapter.ViewHolder(binding);
        }

        @Override
        public void onBindViewHolder(@NonNull RatingsAndReviewsAdapter.ViewHolder holder, int position) {
            NoteEntity ratingFeedback = mList.get(position);

            setFeedback(ratingFeedback.getNote(), holder.mBinding.textViewUserFeedback);
            if (position % 2 == 0) {
                holder.mBinding.getRoot().setBackgroundColor(ContextCompat.getColor(getBaseContext(), android.R.color.white));
            } else {
                holder.mBinding.getRoot().setBackgroundColor(ContextCompat.getColor(getBaseContext(), android.R.color.holo_orange_light));
            }

        }


        private void setFeedback(int feedback, AppCompatTextView textView) {
            textView.setVisibility(View.VISIBLE);
            textView.setText(feedback + "");
        }

        @Override
        public int getItemCount() {
            return mList.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            LayoutUserRatingFeedbackListItemBinding mBinding;

            ViewHolder(LayoutUserRatingFeedbackListItemBinding binding) {
                super(binding.getRoot());
                mBinding = binding;
            }
        }

    }
}