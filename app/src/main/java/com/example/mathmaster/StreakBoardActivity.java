package com.example.mathmaster;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mathmaster.databinding.ActivityStreakBoardBinding;
import com.example.mathmaster.databinding.LayoutStreakListItemBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class StreakBoardActivity extends AppCompatActivity {

    private ActivityStreakBoardBinding mBinding;
    private NoteDao mNoteDao;
    private ArrayList<NoteEntity> mNoteEntityList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_streak_board);

        mNoteDao = NoteRoomDatabase.getDatabase(getApplication()).noteDao();

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
        Intent intent = new Intent(context, StreakBoardActivity.class);
        return intent;
    }

    /*Handle intent*/
    private void handleIntent() {
        if (getIntent() != null) {
        }
    }

    private void initializeRecyclerView(ArrayList<NoteEntity> list) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getBaseContext(), RecyclerView.VERTICAL, false);
        mBinding.recyclerViewStreaks.setLayoutManager(layoutManager);
        mBinding.recyclerViewStreaks.setAdapter(new StreakListAdapter(list));
    }


//    private void getNotes() {
//        new GetNotesAsyncTask(mNoteDao).execute();
//    }

//    private class GetNotesAsyncTask extends AsyncTask<Void, Void, Void> {
//
//        NoteDao mDao;
//
//        public GetNotesAsyncTask(NoteDao noteDao) {
//            mDao = noteDao;
//        }
//
//
//        @Override
//        protected Void doInBackground(Void... voids) {
//
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Void aVoid) {
//            super.onPostExecute(aVoid);
//            if (mNoteEntityList != null && !mNoteEntityList.isEmpty()) {
//                initializeRecyclerView(mNoteEntityList);
//            }
//        }
//    }

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
                        if (noteEntities != null && !noteEntities.isEmpty()) {
                            mBinding.progressBar.setVisibility(View.GONE);
                            mBinding.layoutRetry.setVisibility(View.GONE);
                            mBinding.layoutNoResult.setVisibility(View.GONE);
                            mBinding.layoutMain.setVisibility(View.VISIBLE);
                            mNoteEntityList = noteEntities;
                            initializeRecyclerView(mNoteEntityList);
                        } else {
                            noResultFound("You have not played yet");
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        noResultFound("Error");
                        throwable.printStackTrace();
                    }
                });

    }

    private void noResultFound(String message) {
        mBinding.layoutMain.setVisibility(View.GONE);
        mBinding.progressBar.setVisibility(View.GONE);
        mBinding.layoutRetry.setVisibility(View.GONE);

        mBinding.layoutNoResult.setVisibility(View.VISIBLE);
        mBinding.textViewNoResult.setText(message);
    }


    private class StreakListAdapter extends RecyclerView.Adapter<StreakListAdapter.ViewHolder> {
        private ArrayList<NoteEntity> mList;

        StreakListAdapter(ArrayList<NoteEntity> list) {
            mList = list;
        }

        @NonNull
        @Override
        public StreakListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutStreakListItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.layout_streak_list_item, parent, false);
            return new StreakListAdapter.ViewHolder(binding);
        }

        @Override
        public void onBindViewHolder(@NonNull StreakListAdapter.ViewHolder holder, int position) {
            NoteEntity streakItem = mList.get(position);

            setRanking(position, holder.mBinding.textViewRanking);
            setComputationalType(streakItem.getComputationType(), holder.mBinding.textViewComputationType);
            setStreakCount(streakItem.getStreak(), holder.mBinding.textViewStreak);
            setStreakRecordedDate(streakItem.getStreakRecordedDateInMillis(), holder.mBinding.textViewStreakRecordedDate);
            if (position % 2 == 0) {
                holder.mBinding.getRoot().setBackgroundColor(ContextCompat.getColor(getBaseContext(), android.R.color.white));
            } else {
                holder.mBinding.getRoot().setBackgroundColor(ContextCompat.getColor(getBaseContext(), android.R.color.holo_orange_light));
            }

        }

        private void setRanking(int position, AppCompatTextView textView) {
            String str = String.valueOf(position + 1);
            textView.setText(str + ".");
        }

        private void setComputationalType(String computationType, AppCompatTextView textView) {
            textView.setText(computationType);
        }

        private void setStreakCount(int streak, AppCompatTextView textView) {
            textView.setText(String.valueOf(streak));
        }

        private void setStreakRecordedDate(long streakRecordedDateInMillis, AppCompatTextView textView) {
            SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
            String dateString = formatter.format(new Date(streakRecordedDateInMillis));
            textView.setText(dateString);
        }

        @Override
        public int getItemCount() {
            return mList.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            LayoutStreakListItemBinding mBinding;

            ViewHolder(LayoutStreakListItemBinding binding) {
                super(binding.getRoot());
                mBinding = binding;
            }
        }

    }
}