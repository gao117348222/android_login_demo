package com.gx303.alllogin;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class qqlogin extends baseActivity {

    EditText et_qq_account;//账号输入框
    EditText et_qq_psw;//密码输入框
    ImageView iv_qq_account_del;//账号输入框_删除
    ImageView iv_qq_psw_del;//密码输入框_删除
    ImageView iv_qq_account_arrow;//账号输入框_下拉
    Button btn_qq_login;//登录
    TextView tv_qq_forgetpsw;//无法登录？
    TextView tv_qq_new;//新用户

    LinearLayout ly_qq_1;
    LinearLayout ly_qq_2;
    RelativeLayout ly_qq_3;
    RelativeLayout ly_qq_4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qqlogin);
        init();


        //设置账号输入框和删除图片的显示
        et_qq_account.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus&&!et_qq_account.getText().toString().equals(""))
                {
                    iv_qq_account_del.setVisibility(View.VISIBLE);
                }
                else
                {
                    iv_qq_account_del.setVisibility(View.INVISIBLE);
                }
            }
        });
        et_qq_account.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!et_qq_account.getText().toString().equals(""))
                {
                    iv_qq_account_del.setVisibility(View.VISIBLE);
                }
                else
                {
                    iv_qq_account_del.setVisibility(View.INVISIBLE);
                }
            }
        });

        //设置密码输入框和删除图片的显示
        et_qq_psw.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus&&!et_qq_psw.getText().toString().equals(""))
                {
                    iv_qq_psw_del.setVisibility(View.VISIBLE);
                }
                else
                {
                    iv_qq_psw_del.setVisibility(View.INVISIBLE);
                }
            }
        });
        et_qq_psw.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!et_qq_psw.getText().toString().equals(""))
                {
                    iv_qq_psw_del.setVisibility(View.VISIBLE);
                }
                else
                {
                    iv_qq_psw_del.setVisibility(View.INVISIBLE);
                }
            }
        });


        //设置账号输入框删除图片事件
        iv_qq_account_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_qq_account.setText("");
                et_qq_psw.setText("");
            }
        });
        //设置密码输入框删除图片事件
        iv_qq_psw_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_qq_psw.setText("");
            }
        });


        //密码输入框点击完成之后直接点击确定
        et_qq_psw.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == KeyEvent.ACTION_DOWN || actionId == EditorInfo.IME_ACTION_DONE)
                {
                    btn_qq_login.performClick();
                    return true;
                }
                return false;
            }
        });
        //确定按钮
        btn_qq_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imeDismiss(v);
                T("点击了确定");
            }
        });

        iv_qq_account_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imeDismiss(v);
                initPopAccountLv();
//                lv_pop_account.setAdapter();
                qqlogin_account_listview_adapter adapter =new qqlogin_account_listview_adapter(getApplicationContext());
                lv_pop_account.setAdapter(adapter);
                pop_account_lv.showAsDropDown(et_qq_account,0,0);
            }
        });

        ly_qq_1.setOnClickListener(view_ime_dismissListener);
        ly_qq_2.setOnClickListener(view_ime_dismissListener);
        ly_qq_3.setOnClickListener(view_ime_dismissListener);
        ly_qq_4.setOnClickListener(view_ime_dismissListener);


        //点击忘记密码，弹出忘记密码popupwindows
        tv_qq_forgetpsw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initPopForgetPsw();
                pop_forgetpsw.showAtLocation(v, Gravity.CENTER,0,0);
            }
        });
        tv_qq_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                T("新用户");
            }
        });
    }
    View.OnClickListener view_ime_dismissListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            imeDismiss(v);
        }
    };
    public void imeDismiss(View v)
    {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0); //强制隐藏键盘
    }

    public void init()
    {
        et_qq_account=(EditText)findViewById(R.id.et_qq_account);
        et_qq_psw=(EditText)findViewById(R.id.et_qq_psw);
        iv_qq_account_del=(ImageView)findViewById(R.id.iv_qq_account_del);
        iv_qq_psw_del=(ImageView)findViewById(R.id.iv_qq_psw_del);
        iv_qq_account_arrow=(ImageView)findViewById(R.id.iv_qq_account_arrow);
        btn_qq_login=(Button)findViewById(R.id.btn_qq_login);
        tv_qq_forgetpsw=(TextView)findViewById(R.id.tv_qq_forgetpsw);
        tv_qq_new=(TextView)findViewById(R.id.tv_qq_new);

        ly_qq_1=(LinearLayout)findViewById(R.id.ly_qq_1);
        ly_qq_2=(LinearLayout)findViewById(R.id.ly_qq_2);
        ly_qq_3=(RelativeLayout)findViewById(R.id.ly_qq_3);
        ly_qq_4=(RelativeLayout)findViewById(R.id.ly_qq_4);
    }
    PopupWindow pop_account_lv;
    ListView lv_pop_account;
    public void initPopAccountLv()
    {
        if(pop_account_lv==null)
        {
            View view=getLayoutInflater().inflate(R.layout.qq_account_lv,null);
            lv_pop_account=(ListView)view.findViewById(R.id.lv_1);
            pop_account_lv=new PopupWindow(view, LinearLayout.LayoutParams.FILL_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
            // 需要设置一下此参数，点击外边可消失
            pop_account_lv.setBackgroundDrawable(new BitmapDrawable());
            //设置点击窗口外边窗口消失
            pop_account_lv.setOutsideTouchable(true);
            // 设置此参数获得焦点，否则无法点击
            pop_account_lv.setFocusable(true);
            pop_account_lv.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {

                }
            });
        }
    }

    PopupWindow pop_forgetpsw;//忘记密码popupwindows
    LinearLayout lay_pop_forgetpsw_zhmm;//找回密码
    LinearLayout lay_pop_forgetpsw_dxyzdl;//短信验证登录
    LinearLayout lay_pop_forgetpsw_cancel;//取消
    RelativeLayout lay_pop_forgetpsw_back;
    public void initPopForgetPsw()
    {
        if(pop_forgetpsw==null)
        {
            View view=getLayoutInflater().inflate(R.layout.qq_forget_psw_pop,null);
            lay_pop_forgetpsw_zhmm=(LinearLayout)view.findViewById(R.id.lay_zhmm);
            lay_pop_forgetpsw_dxyzdl=(LinearLayout)view.findViewById(R.id.lay_dxyz);
            lay_pop_forgetpsw_cancel=(LinearLayout)view.findViewById(R.id.lay_cancel);
            lay_pop_forgetpsw_back=(RelativeLayout)view.findViewById(R.id.main_back);
            pop_forgetpsw=new PopupWindow(view,LinearLayout.LayoutParams.FILL_PARENT,LinearLayout.LayoutParams.FILL_PARENT);
            pop_forgetpsw.setFocusable(true);
            lay_pop_forgetpsw_back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pop_forgetpsw.dismiss();
                }
            });
            lay_pop_forgetpsw_zhmm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    T("找回密码");
                    pop_forgetpsw.dismiss();
                }
            });
            lay_pop_forgetpsw_dxyzdl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    T("短信验证登录");
                    pop_forgetpsw.dismiss();
                }
            });
            lay_pop_forgetpsw_cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pop_forgetpsw.dismiss();
                }
            });


        }
    }


}
