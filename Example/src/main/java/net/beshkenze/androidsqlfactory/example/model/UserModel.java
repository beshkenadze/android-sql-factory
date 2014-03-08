package net.beshkenze.androidsqlfactory.example.model;

import android.content.Context;
import net.beshkenze.androidsqlfactory.library.Model;

/**
 * Created by Aleksandr Beshkenadze <beshkenadze@gmail.com> on 01.03.14.
 */
public class UserModel extends Model {
    public static final String TABLE_NAME = "users";

    public UserModel(Context context) {
        super(context);
    }
}
