package com.gx303.alllogin;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Administrator on 2015/5/12.
 */
public class baseActivity extends Activity {

    public void L(String s)
    {
        Log.e("Unity", s);
    }
    public void T(String s)
    {
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
    }
}
