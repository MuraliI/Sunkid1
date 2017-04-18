package com.rcl.excalibur.data.repository;


import android.support.annotation.NonNull;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Model;
import com.activeandroid.query.Select;
import com.rcl.excalibur.data.mapper.BaseDataMapper;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

import static com.rcl.excalibur.data.utils.DBUtil.eq;

public abstract class BaseDataRepository<O, I extends Model, T, M extends BaseDataMapper<O, I, T>> {

    private final M dataMapper;
    private final Class<I> claz;

    protected BaseDataRepository(M dataMapper, Class<I> claz) {
        this.dataMapper = dataMapper;
        this.claz = claz;
    }

    protected BaseDataMapper<O, I, T> getMapper() {
        return dataMapper;
    }

    public abstract void create(@NonNull O input);

    public void create(List<O> inputList) {
        new Thread(() -> {
            try {
                ActiveAndroid.beginTransaction();
                try {
                    for (O item : inputList) {
                        create(item);
                    }
                    ActiveAndroid.setTransactionSuccessful();
                } finally {
                    ActiveAndroid.endTransaction();
                }
            } catch (Exception e) {
                Timber.e(e.getMessage());
            }
        }).start();
    }

    public void getAll(Observer<List<O>> observer) {
        Observable.create((ObservableOnSubscribe<List<O>>) e -> {
            final List<I> entities = new Select()
                    .from(claz)
                    .execute();
            e.onNext(dataMapper.transform(entities, null));
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public O get(@NonNull String column, final String value) {
        final I entity = new Select()
                .from(claz)
                .where(eq(column, value))
                .executeSingle();
        return dataMapper.transform(entity, null);
    }

    public abstract void deleteAll();

}
