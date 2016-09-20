package com.emaildemo.com;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.emaildemo.com.email.MailSenderInfo;
import com.emaildemo.com.email.SimpleMailSender;

public class MainActivity extends AppCompatActivity {

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.edittext);
    }

    public void sendEmail(View view) {
        new Thread( new Runnable(){

            @Override
            public void run() {
                MailSenderInfo mailInfo = new MailSenderInfo();
                mailInfo.setMailServerHost("smtp.yeah.net");// 发送邮件的服务器的IP
                mailInfo.setMailServerPort("25");//// 发送邮件的服务器端口
                mailInfo.setValidate(true);
                mailInfo.setUserName("charcolee@yeah.net"); // 你的邮箱地址
                mailInfo.setPassword("de3366526");// 您的邮箱登录授权码而非密码
                mailInfo.setFromAddress("charcolee@yeah.net");//发送邮箱
                mailInfo.addToAddress(editText.getText().toString());//接收邮箱,可填多个
                mailInfo.setSubject("这是一封邮件");//邮件主题
                mailInfo.setContent("这是邮件的内容");//邮件内容
                final boolean success = SimpleMailSender.sendTextMail(mailInfo);
                editText.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),success
                                ? "发送成功":"发生失败",Toast.LENGTH_LONG).show();
                    }
                });
            }
        }).start();

    }
}
