package net.beshkenze.androidsqlfactory.example.model;

import android.content.Context;
import net.beshkenze.androidsqlfactory.library.Model;

/**
 * Created by Aleksandr Beshkenadze <beshkenadze@gmail.com> on 01.03.14.
 */
public class SmsModel extends Model {
    public static final String TABLE_NAME = "sms";
    public static final String _ID = TABLE_NAME + "._id";
    public static final String NUMBER = TABLE_NAME + ".number";
    public static final String CREATED_AT = TABLE_NAME + ".created_at";

    public SmsModel(Context context) {
        super(context);
    }
}
