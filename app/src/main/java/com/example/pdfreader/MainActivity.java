package com.example.pdfreader;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.os.Environment;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    Button clck;
    int pageWidth=1200;
    //String[] informationArray=new String[]{"Name: ", "Email: ","Phone: ","Hospital Name: "};
    EditText Email,Name, Phone;
    Date dateObj;
    DateFormat dateFormat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        clck=findViewById(R.id.click);

        Email=findViewById(R.id.email);
        Name=findViewById(R.id.name);
        Phone=findViewById(R.id.phone);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);
        createPDF();
    }

    private void createPDF() {
        clck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dateObj=new Date();

                if (Email.getText().toString().length()==0 || Name.getText().toString().length()==0 || Phone.getText().toString().length()==0){
                    Toast.makeText(MainActivity.this, "Some Fields Empty", Toast.LENGTH_SHORT).show();
                }
                else {
                    PdfDocument myPdfDocument=new PdfDocument();
                    Paint myPaint=new Paint();
                    Paint titlePaint=new Paint();

                    PdfDocument.PageInfo myPageInfo1= new PdfDocument.PageInfo.Builder(1200, 2010, 1).create();
                    PdfDocument.Page myPage1=myPdfDocument.startPage(myPageInfo1);
                    Canvas canvas=myPage1.getCanvas();

                    titlePaint. setTextAlign (Paint.Align. CENTER);
                    titlePaint . setTypeface (Typeface. create(Typeface. DEFAULT, Typeface.ITALIC));
                    titlePaint. setTextSize (70);
                    canvas.drawText ("Invoice", pageWidth/2, 100, titlePaint) ;

                    myPaint. setTextAlign (Paint .Align. LEFT);
                    myPaint. setTextSize (35f);
                    myPaint.setColor (Color . BLACK);
                    canvas.drawText("Customer Name:"+Name. getText (),20,290,myPaint);
                    canvas.drawText("Contact No. :" +Phone.getText (), 20, 350, myPaint);
                    canvas.drawText("Email: "+Email.getText(),20 ,410 ,myPaint );

                    myPdfDocument.finishPage (myPage1);

                    File file=new File(Environment.getExternalStorageDirectory(),"/Hello111.pdf");
                    try {
                        myPdfDocument.writeTo(new FileOutputStream(file));
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                    myPdfDocument.close();
                    Toast.makeText(MainActivity.this, "PDF Created", Toast.LENGTH_SHORT).show();
                }


                /*myPaint.setTextAlign(Paint.Align.CENTER);
                myPaint.setTextSize(12f);
                canvas.drawText("Bikash Enterprises", myPageInfo1.getPageWidth(), 30, myPaint);

                myPaint. setTextSize (6.0f) ;
                myPaint.setTextScaleX(1.5f);
                myPaint.setColor (Color. rgb(122,119,119));
                canvas.drawText ("Cuttack, Odisha, India",myPageInfo1.getPageWidth()/2,40, myPaint) ;
                myPaint.setTextScaleX(1f);

                myPaint.setTextAlign (Paint.Align . LEFT);
                myPaint.setTextSize (9.0f);
                myPaint.setColor (Color. rgb(122,119,119));
                canvas.drawText ("Customer Information",10, 70, myPaint) ;
                myPaint.setTextAlign (Paint.Align.LEFT);
                myPaint.setTextSize (8.0f);
                myPaint.setColor (Color.BLACK);

                int startXposition=10;
                int endXposition=myPageInfo1.getPageWidth()-10;
                int startYposition=100;

                for (int i = 0; i<5; i++){
                    canvas.drawText(informationArray[i],startXposition,startYposition,myPaint);
                    canvas.drawLine(startXposition, startYposition, endXposition, startYposition+3, myPaint);
                    startYposition+=20;
                }
                canvas.drawLine(80,92,80,190,myPaint);

                myPaint. setStyle (Paint . Style . STROKE);
                myPaint.setStrokeWidth (2);
                canvas.drawRect (10, 200, myPageInfo1 .getPageWidth ()-10, 300, myPaint);
                canvas.drawLine ( 85, 200,85,300, myPaint) ;
                canvas.drawLine ( 163, 200, 163, 300,myPaint) ;
                myPaint.setStrokeWidth (0);
                myPaint.setStyle (Paint. Style. FILL);
                canvas.drawText ("Photo",35, 250, myPaint);
                canvas.drawText ( "Photo", 110, 250, myPaint);
                canvas.drawText ( "Photo", 190, 250, myPaint);
                canvas.drawText( "Note:", 10, 320, myPaint);
                canvas.drawLine ( 35, 325, myPageInfo1.getPageWidth()-10, 325, myPaint);
                canvas.drawLine ( 10, 345, myPageInfo1.getPageWidth ()-10, 345, myPaint);
                canvas.drawLine ( 10, 369, myPageInfo1.getPageWidth ()-10,365, myPaint);*/


            }
        });
    }
}