package com.gx303.alllogin;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;


public class MainActivity extends baseActivity {

    Button btn_zhizhu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_zhizhu=(Button)findViewById(R.id.btn_zhizhu);
    }



    PopupWindow pop_zhihu;//知乎_popupwindows
    RelativeLayout zhihu_lay_cancel;//知乎_取消_外层layout
    RelativeLayout zhihu_lay_confirm;//知乎_确定_外层layout
    RelativeLayout zhihu_lay_back;//知乎_最外层半透明背景
    EditText zhihu_et_email;//知乎_输入框_email
    EditText zhihu_et_psw;//知乎_输入框_密码
    TextView zhihu_tv_confirm;//知乎_文本_确定
    LinearLayout zhihu_lay_center;//知乎_中间区域layout
    ImageView zhihu_iv_forgetpsw;//知乎_图片_忘记密码
    public void initZhihu()
    {
        if(pop_zhihu==null)
        {
            View view=getLayoutInflater().inflate(R.layout.zhihu_login_pop,null);
            pop_zhihu=new PopupWindow(view, LinearLayout.LayoutParams.FILL_PARENT,LinearLayout.LayoutParams.FILL_PARENT);
            pop_zhihu.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
            zhihu_lay_cancel=(RelativeLayout)view.findViewById(R.id.zhihu_lay_cancel);
            zhihu_lay_confirm=(RelativeLayout)view.findViewById(R.id.zhihu_lay_confirm);
            zhihu_lay_back=(RelativeLayout)view.findViewById(R.id.zhihu_lay_back);
            zhihu_et_email=(EditText)view.findViewById(R.id.zhihu_et_email);
            zhihu_et_psw=(EditText)view.findViewById(R.id.zhihu_et_psw);
            zhihu_tv_confirm=(TextView)view.findViewById(R.id.zhihu_tv_confirm);
            zhihu_lay_center=(LinearLayout)view.findViewById(R.id.zhihu_lay_center);
            zhihu_iv_forgetpsw=(ImageView)view.findViewById(R.id.zhihu_iv_forgetpsw);
            // 需要设置一下此参数，点击外边可消失
            pop_zhihu.setBackgroundDrawable(new BitmapDrawable());
            //设置点击窗口外边窗口消失
            pop_zhihu.setOutsideTouchable(true);
            // 设置此参数获得焦点，否则无法点击
            pop_zhihu.setFocusable(true);
            //点击取消layout，popupwindows消失
            zhihu_lay_cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pop_zhihu.dismiss();
                }
            });
            //点击半透明背景，popupwindows消失
            zhihu_lay_back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pop_zhihu.dismiss();
                }
            });
            //点击中间白色区域，popupwindows不消失
            zhihu_lay_center.setOnClickListener(null);
            //两个输入框的事件，若有一个输入框为空，确定layout无法点击
            zhihu_et_email.addTextChangedListener(zhihu_edittext_tw);
            zhihu_et_psw.addTextChangedListener(zhihu_edittext_tw);
            //密码输入框点击回车的事件，相当于点击了确定layout
            zhihu_et_psw.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if (actionId == KeyEvent.ACTION_DOWN || actionId == EditorInfo.IME_ACTION_DONE)
                    {
                        zhihu_lay_confirm.performClick();
                        return true;
                    }
                    return false;
                }
            });
            //忘记密码图片点击事件
            zhihu_iv_forgetpsw.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    T("点击了忘记密码");
                    pop_zhihu.dismiss();
                }
            });

        }
    }
    //确定layout点击事件
    View.OnClickListener zhihu_lay_confirm_clickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            T("点击了确定");
            pop_zhihu.dismiss();
        }
    };
    //两个输入框的事件，若有一个输入框为空，确定layout无法点击
    TextWatcher zhihu_edittext_tw=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

            if(zhihu_et_email.getText().toString().equals("")||zhihu_et_psw.getText().toString().equals(""))
            {
                //确定按钮不可点击
                zhihu_lay_confirm.setOnClickListener(null);
                zhihu_tv_confirm.setTextColor(getResources().getColor(R.color.zhihu_button_confirm_unclick));
            }
            else
            {
                //这里把确定按钮搞成可以点击
                zhihu_lay_confirm.setOnClickListener(zhihu_lay_confirm_clickListener);
                zhihu_tv_confirm.setTextColor(getResources().getColor(R.color.black));
            }
        }
    };

    /*
    知乎app无法反编译出res
     */
    public void zhihuLogin(View v)
    {
        initZhihu();
        pop_zhihu.showAtLocation(btn_zhizhu, Gravity.CENTER,0,0);

    }
    /*
    qq登录
    qq登录界面，有意思的地方在于弹出输入法后，界面上移的处理
    关键在于：把不需要显示的layout设置成为0高度，用weight设置高度的layout，这样子，需要显示的layout就会显示，不需要显示的会被隐藏
     */
    public void qq_login(View v)
    {
        Intent it_qqLogin=new Intent();
        it_qqLogin.setClass(getApplicationContext(),qqlogin.class);
        startActivity(it_qqLogin);
    }
    /*
    淘宝登陆的
     */
    public void opentblogin(View v)
    {

        Intent it_tblogin=new Intent();
        it_tblogin.setClass(getApplicationContext(),tblogin.class);
        startActivity(it_tblogin);
    }
    /*
    打开支付宝钱包
     */
    public void  openZFBQB(View v)
    {
        Intent it_zfb=new Intent();
        it_zfb.setClass(getApplicationContext(),zfbqb.class);
        startActivity(it_zfb);
    }
}
