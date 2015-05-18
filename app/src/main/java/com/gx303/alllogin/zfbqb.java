package com.gx303.alllogin;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;


public class zfbqb extends baseActivity {

    ImageView iv_top;//最上面的图片
    EditText et_account;//账号输入
    EditText et_psw;//密码输入
    LinearLayout lay_login;//登录框
    TextView tv_login;//登录文字
    TextView tv_zhdl;//账户登录切换
    TextView tv_wjmm;//忘记密码
    LinearLayout lay_zczh;//注册账号
    ImageView img_account_del;//账号删除
    ImageView img_psw_del;//密码删除
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zfbqb);
        init();
        tv_zhdl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangeJM();
            }
        });
        //登录本来是暗色，两个edittext都有值就会变白色
        et_account.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                checkCanLogin();
                if(et_account.getText().toString().equals(""))
                {
                    img_account_del.setVisibility(View.INVISIBLE);
                }
                else
                {
                    img_account_del.setVisibility(View.VISIBLE);
                }
            }
        });
        img_account_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_account.setText("");
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
                checkCanLogin();
                if(et_psw.getText().toString().equals(""))
                {
                    img_psw_del.setVisibility(View.INVISIBLE);
                }
                else
                {
                    img_psw_del.setVisibility(View.VISIBLE);
                }
            }
        });
        img_psw_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_psw.setText("");
            }
        });
        lay_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(canlogin)
                {
                    T("可以登录");
                }
            }
        });

        tv_wjmm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                T("支付宝忘记密码");
            }
        });
        lay_zczh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                T("支付宝账号注册");
            }
        });

    }
    boolean canlogin=false;
    public void checkCanLogin()
    {
        if(!et_account.getText().toString().equals("")&&!et_psw.getText().toString().equals(""))
        {
            canlogin=true;
            tv_login.setTextColor(Color.WHITE);
//            lay_login.setBackgroundResource(getResources().getDrawable(R.drawable.zfb_login_layout));
            lay_login.setBackgroundResource(R.drawable.zfb_login_layout);
        }
        else
        {
            canlogin=false;
            tv_login.setTextColor(Color.parseColor("#304E6D"));
            lay_login.setBackgroundResource(R.drawable.zfb_login_layout_cantpress);
        }
    }
    public  void  init()
    {
        iv_top=(ImageView)findViewById(R.id.iv_top);
        et_account=(EditText)findViewById(R.id.et_account);
        et_psw=(EditText)findViewById(R.id.et_psw);
        lay_login=(LinearLayout)findViewById(R.id.lay_login);
        tv_login=(TextView)findViewById(R.id.tv_login);
        tv_zhdl=(TextView)findViewById(R.id.tv_zhdl);
        tv_wjmm=(TextView)findViewById(R.id.tv_wjmm);
        lay_zczh=(LinearLayout)findViewById(R.id.lay_zczh);
        img_account_del=(ImageView)findViewById(R.id.img_account_del);
        img_psw_del=(ImageView)findViewById(R.id.img_psw_del);

    }
    boolean isZFBJM=true;//是否是支付宝界面
    /*
    修改界面
     */
    public void ChangeJM()
    {
        et_account.setText("");
        et_psw.setText("");

        if(isZFBJM)
        {
            //支付宝界面
            iv_top.setImageDrawable(getResources().getDrawable(R.drawable.zfb_zfbimg));
            et_account.setHint("手机号/邮箱");
            et_psw.setHint("支付宝登录密码");
            tv_zhdl.setText("淘宝账户登录");
            tv_wjmm.setVisibility(View.VISIBLE);
            lay_zczh.setVisibility(View.VISIBLE);
        }
        else
        {
            //淘宝界面
            iv_top.setImageDrawable(getResources().getDrawable(R.drawable.zfb_tbimg));
            et_account.setHint("手机号/邮箱/会员名");
            et_psw.setHint("淘宝登录密码");
            tv_zhdl.setText("支付宝账户登录");
            tv_wjmm.setVisibility(View.INVISIBLE);
            lay_zczh.setVisibility(View.INVISIBLE);
        }
        isZFBJM=!isZFBJM;
    }



}
