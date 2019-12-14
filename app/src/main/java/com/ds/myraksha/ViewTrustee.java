package com.ds.myraksha;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;

public class ViewTrustee extends AppCompatActivity {


    TextView name,num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_trustee);

        name = (TextView) findViewById(R.id.showname);
        num = (TextView) findViewById(R.id.shownum);



        try {
            FileInputStream fileInputStream =  openFileInput("Raksha.txt");
            int read = -1;
            StringBuffer buffer = new StringBuffer();
            while((read =fileInputStream.read())!= -1){
                buffer.append((char)read);
            }
            Log.d("Code", buffer.toString());
            String sname = buffer.substring(0,buffer.indexOf(" "));
            String snum = buffer.substring(buffer.indexOf(" ")+1);
            name.setText(sname);
            num.setText(snum);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
