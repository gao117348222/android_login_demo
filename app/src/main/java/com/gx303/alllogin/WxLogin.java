package com.gx303.alllogin;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class WxLogin extends baseActivity {

    LinearLayout lay_top_left;//左上角关闭layout
    LinearLayout lay_account;//账号layout
    LinearLayout lay_psw;//密码layout
    EditText et_account;//账号输入框
    EditText et_psw;//密码输入框
    ImageView iv_account_del;//账号删除
    ImageView iv_psw_del;//密码删除
    LinearLayout lay_login;//登录layout

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wx_login);
        init();
        //点击左上角直接关闭
        lay_top_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        et_account.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    //
                    lay_account.setBackgroundResource(R.drawable.wx_edit_lay_press);
                } else {
                    lay_account.setBackgroundResource(R.drawable.wx_edit_lay_normal_1);
                }
            }
        });
        et_psw.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus)
                {
                    //
                    lay_psw.setBackgroundResource(R.drawable.wx_edit_lay_press);
                }
                else
                {
                    lay_psw.setBackgroundResource(R.drawable.wx_edit_lay_normal_1);
                }
            }
        });
        et_account.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                canlogin();
                if (!et_account.getText().toString().equals("")) {
                    iv_account_del.setVisibility(View.VISIBLE);
                } else {
                    iv_account_del.setVisibility(View.INVISIBLE);
                }
            }
        });
        et_psw.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                canlogin();
                if (!et_psw.getText().toString().equals("")) {
                    iv_psw_del.setVisibility(View.VISIBLE);
                } else {
                    iv_psw_del.setVisibility(View.INVISIBLE);
                }
            }
        });
        //密码输入点击done事件
        et_psw.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == KeyEvent.ACTION_DOWN || actionId == EditorInfo.IME_ACTION_DONE)
                {
                    lay_login.performClick();
                    return true;
                }
                return false;
            }
        });
        lay_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(canlogin)
                {
                    T("点击登录");
                }
            }
        });
        //删除图片
        iv_account_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_account.setText("");
            }
        });
        iv_psw_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_psw.setText("");
            }
        });
    }

    public void init()
    {
        lay_top_left=(LinearLayout)findViewById(R.id.lay_top_left);
        lay_account=(LinearLayout)findViewById(R.id.lay_account);
        lay_psw=(LinearLayout)findViewById(R.id.lay_psw);
        et_account=(EditText)findViewById(R.id.et_account);
        et_psw=(EditText)findViewById(R.id.et_psw);
        iv_account_del=(ImageView)findViewById(R.id.iv_account_del);
        iv_psw_del=(ImageView)findViewById(R.id.iv_psw_del);
        lay_login=(LinearLayout)findViewById(R.id.lay_login);
    }
    boolean canlogin=false;
    public void canlogin()
    {
        if(!et_account.getText().toString().equals("")&&!et_psw.getText().toString().equals(""))
        {
            canlogin=true;
            lay_login.setBackgroundResource(R.drawable.wx_login_lay);
        }
        else
        {
            canlogin=false;
            lay_login.setBackgroundResource(R.drawable.wx_login_lay_cantpress);
        }
    }
}
