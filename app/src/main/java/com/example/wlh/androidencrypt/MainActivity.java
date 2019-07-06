package com.example.wlh.androidencrypt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String AES_Algorithm = "AES";
    private static final String AES_CBC_Transformation = "AES/CBC/PKCS5Padding";
    private TextView encryptData;
    private TextView origonData;
    private EditText input_data;
    private String inputStr;
    private static byte []result;
    //    private Button encypt;
//    private Button crypt;
    private final byte[] mKey = {
        1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16
    };

/*    private final byte[] mKey = {
            1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,
            21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,
            41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,
            61,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,80,
            81,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,100,
            101,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,118,19,120,
            1,2,3,4,5,6,7,8
    };*/
    private byte []data = {1,2};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.encrypt).setOnClickListener(this);
        encryptData = (TextView) findViewById(R.id.encrypt_data);
        findViewById(R.id.crypt).setOnClickListener(this);
        origonData = (TextView) findViewById(R.id.origon_data);
        input_data = (EditText) findViewById(R.id.inputdata);

    }



    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.encrypt:
            {
                inputStr = input_data.getText().toString();
                result =  EncryptAES(data,mKey);
            }
                break;
            case R.id.crypt:
                result = DecryptAES(result ,mKey);
                break;
        }
    }

    public static byte[] EncryptAES(byte [] data, byte[] key){
        return MainActivity.desTemplate(data,key, AES_Algorithm, AES_CBC_Transformation,true);
    }

    public static byte[] DecryptAES(byte [] data, byte[] key){
        return MainActivity.desTemplate(data,key, AES_Algorithm, "AES/CBC",false);
    }

    public static byte [] desTemplate(byte [] data, byte[] key, String algorithm, String transformation,boolean isEncrypt){
        if (data == null || data.length == 0 || key == null || key.length == 0) {
            return null;
        }

        try {
            SecretKeySpec keySpec = new SecretKeySpec(key,algorithm);
            Cipher cipher = Cipher.getInstance(transformation);
            //SecureRandom random = new SecureRandom();
            cipher.init(isEncrypt?Cipher.ENCRYPT_MODE:Cipher.DECRYPT_MODE,keySpec/*,random*/);
            return cipher.doFinal(data);

        }catch (Throwable e){
            e.printStackTrace();
            return null;
        }

    }

 }
