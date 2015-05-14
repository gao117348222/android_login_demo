package com.gx303.alllogin;

import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;


public class tblogin extends baseActivity {

    TextView tb_login_top_tv;//左上角的淘宝账户登陆，
    EditText tb_login_et_account;//账号输入框
    ImageView tb_login_iv_account_del;//账号输入框删除按钮
    EditText tb_login_et_psw;//密码输入框
    ImageView tb_login_iv_psw_del;//密码输入框删除按钮
    ImageView tb_login_iv_psw_see;//密码输入框显示按钮
    LinearLayout tb_login_login_button_lay;//登陆layout
    TextView tb_login_login_tv_lay;//登陆
    TextView tv_kszc;//快速注册
    TextView tv_zhmm;//找回密码
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tblogin);
        init();
        //点击左上角界面消失
        tb_login_top_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();;
            }
        });
        //两个输入框，只要有输入，都会显示两侧的删除按钮，失去焦点的时候不显示
        tb_login_et_account.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                //显示删除imageview
                tb_login_iv_account_del.setVisibility(View.VISIBLE);
                checkEdittextEmpey();
            }
        });
        tb_login_et_psw.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                tb_login_iv_psw_del.setVisibility(View.VISIBLE);
                checkEdittextEmpey();
            }
        });
        //失去焦点的时候不显示
        tb_login_et_account.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                {
                    tb_login_iv_account_del.setVisibility(View.INVISIBLE);
                }
            }
        });
        tb_login_et_psw.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                tb_login_iv_psw_del.setVisibility(View.INVISIBLE);
            }
        });

        //点击登录
        tb_login_login_button_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(canLogin)
                {
                    T("处理登录");
                }
            }
        });
        //是否显示密码
        tb_login_iv_psw_see.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isShowPsw)
                {
                    //这里把密码隐藏
                    tb_login_et_psw.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    tb_login_iv_psw_see.setImageDrawable(getResources().getDrawable(R.drawable.tb_login_see));

                }
                else
                {
                    //这里把密码显示
                    tb_login_et_psw.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    tb_login_iv_psw_see.setImageDrawable(getResources().getDrawable(R.drawable.tb_login_unsee));
                }
                isShowPsw=!isShowPsw;
            }
        });

        //删除文字
        tb_login_iv_account_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tb_login_et_account.setText("");
            }
        });
        tb_login_iv_psw_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tb_login_et_psw.setText("");
            }
        });

        tv_kszc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                T("快速注册");
            }
        });
        tv_zhmm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                T("找回密码");
            }
        });

    }
    boolean isShowPsw=false;//最初是不显示密码的

    boolean canLogin=false;
    /*
    检查两个输入框是否有空的，有空的登录按钮文字变色，不可点击
     */
    public void checkEdittextEmpey()
    {
        if(tb_login_et_account.getText().toString().equals("")||tb_login_et_psw.getText().toString().equals(""))
        {
            canLogin=false;
            //按钮变暗色
            tb_login_login_tv_lay.setTextColor(Color.rgb(238,133,97));
        }
        else
        {
            canLogin=true;
            //按钮变白色
            tb_login_login_tv_lay.setTextColor(Color.WHITE);
        }
    }

    public void init()
    {
        tb_login_top_tv=(TextView)findViewById(R.id.tb_login_top_tv);
        tb_login_et_account=(EditText)findViewById(R.id.tb_login_et_account);
        tb_login_iv_account_del=(ImageView)findViewById(R.id.tb_login_iv_account_del);
        tb_login_et_psw=(EditText)findViewById(R.id.tb_login_et_psw);
        tb_login_iv_psw_del=(ImageView)findViewById(R.id.tb_login_iv_psw_del);
        tb_login_iv_psw_see=(ImageView)findViewById(R.id.tb_login_iv_psw_see);
        tb_login_login_button_lay=(LinearLayout)findViewById(R.id.tb_login_login_button_lay);
        tb_login_login_tv_lay=(TextView)findViewById(R.id.tb_login_login_tv_lay);
        tv_kszc=(TextView)findViewById(R.id.tv_kszc);
        tv_zhmm=(TextView)findViewById(R.id.tv_zhmm);
    }



}
