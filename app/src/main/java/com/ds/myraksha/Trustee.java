package com.ds.myraksha;

import android.app.PendingIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import es.dmoral.toasty.Toasty;

public class Trustee extends AppCompatActivity {

    TextView showname,shownum;
    EditText editname,editnum;
    Button b1;
    CheckBox cb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trustee);

        showname=findViewById(R.id.showname);
        shownum=findViewById(R.id.shownum);
        editname=findViewById(R.id.editname);
        editnum=findViewById(R.id.editnum);
        b1= findViewById(R.id.button);
        cb=findViewById(R.id.cbox);

    }

    public void  change(View view)  // SAVE
    {
        File file= null;
        String name = editname.getText().toString();
        String number = editnum.getText().toString();
        if(TextUtils.isEmpty(name))
        {
            Toasty.warning(getApplicationContext(),"Please enter a name",Toast.LENGTH_SHORT).show();
            return;
        }
        if(number.length()!=10)
        {
            Toasty.warning(getApplicationContext(),"Enter a valid mobile number",Toast.LENGTH_LONG).show();
            return;
        }


        FileOutputStream fileOutputStream = null;
        try {
            name = name + " ";
            file = getFilesDir();
            fileOutputStream = openFileOutput("Raksha.txt", Context.MODE_PRIVATE); //MODE PRIVATE
            fileOutputStream.write(name.getBytes());
            fileOutputStream.write(number.getBytes());
            Toast.makeText(this, "Saved \n" + "Path --" + file + "\tRaksha.txt", Toast.LENGTH_SHORT).show();
            editname.setText("");
            editnum.setText("");
            if(cb.isChecked())
            {
                Intent intent = new Intent(getApplicationContext(), Trustee.class);
                PendingIntent pi = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);
                SmsManager sms = SmsManager.getDefault();
                sms.sendTextMessage(number, null, "I have added you as my Trustee in MY RAKSHA app.\n You can install this helpful app here http://play.google.com/store/apps/details?id=com.ds.myraksha", pi, null);
                Toasty.success(getApplicationContext(), "Notified and Details updated", Toast.LENGTH_SHORT).show();
            }

            return;

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    public void  next( View view)   //change
    {
        Intent intent= new Intent(this, MainActivity.class);
        startActivity(intent);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent bk=new Intent(getApplicationContext(),MainActivity.class);
        startActivity(bk);
    }
}
