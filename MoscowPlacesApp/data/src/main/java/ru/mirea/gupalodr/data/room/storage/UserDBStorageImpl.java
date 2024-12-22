package ru.mirea.gupalodr.data.room.storage;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import ru.mirea.gupalodr.data.room.DBCallback;
import ru.mirea.gupalodr.data.room.UserDBStorage;
import ru.mirea.gupalodr.data.room.UserDB;

public class UserDBStorageImpl implements UserDBStorage {
    private UserDB userDB;
    private Context context;
    private final ExecutorService databaseExecutor = Executors.newSingleThreadExecutor();
    private final Handler mainThreadHandler = new Handler(Looper.getMainLooper());

    public void UserDBStorageImpl(Context context) {
        this.context = context;
        createDB();
    }

    private void createDB() {
        RoomDatabase.Callback callback = new RoomDatabase.Callback() {
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);
            }

            @Override
            public void onOpen(@NonNull SupportSQLiteDatabase db) {
                super.onOpen(db);
            }
        };

        userDB = Room.databaseBuilder(context, UserDB.class, "users")
                .addCallback(callback)
                .build();
    }

    @Override
    public void addUser(ru.mirea.gupalodr.data.room.models.UserDB user) {
        databaseExecutor.execute(() -> userDB.getUserDao().addUser(user));
    }

    @Override
    public void getUser(String login, DBCallback<ru.mirea.gupalodr.data.room.models.UserDB> callback) {
        databaseExecutor.execute(() -> {
            ru.mirea.gupalodr.data.room.models.UserDB user = userDB.getUserDao().getUser(login);
            if (user != null) {
                mainThreadHandler.post(() -> callback.onSuccess(user));
            } else {
                mainThreadHandler.post(() -> callback.onError(new Exception("Пользователь не найден")));
            }
        });
    }
}
