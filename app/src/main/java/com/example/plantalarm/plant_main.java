package com.example.plantalarm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class plant_main extends AppCompatActivity {
    TextView TV_growingDate;
    TextView TV_serviveDate;
    TextView TV_plantNickname;
    ImageView IV_plantImage;
    EditText ET_message;
    int[][] imageID;

    // ---------- 데이터베이스 부분--------- //
    plantDB helper;
    SQLiteDatabase db;

    // ---------데이터베이스 부분--------- //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(Plant.plantNickname==null){
            Plant.plantNickname="튼튼이";
        }
        if(Plant.isDead){

            Intent intent = new Intent(getApplicationContext(), plant_select.class);
            startActivity(intent);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plant_main);
        TV_growingDate = (TextView)findViewById(R.id.textView_plant_growing_date);
        TV_serviveDate = (TextView)findViewById(R.id.TextView_plant_servive_date);
        TV_plantNickname = (TextView) findViewById(R.id.TextView_plant_nickname);
        IV_plantImage = (ImageView) findViewById(R.id.imageView_plant_image);
        ET_message = (EditText) findViewById(R.id.editTextText_message);

        imageID = new int[][]{{R.drawable.plant0_0, R.drawable.plant0_1,R.drawable.plant0_2,R.drawable.plant0_3 },{
                R.drawable.plant0_0, R.drawable.plant2_1, R.drawable.plant2_1,R.drawable.plant2_1}
        };
        updatePlantInfoText();
        TV_plantNickname.setText(Plant.getPlantNickname());
        IV_plantImage.setImageResource(imageID[Plant.typeOfPlant][Plant.growthState]);

        // 데이터베이스 부분
//        helper = new plantDB(this);
//        db = helper.getWritableDatabase();
//
//        helper.initPlantLevelImage();



        // 데이터베이스 부분
        helper = new plantDB(this);
        db = helper.getWritableDatabase();

        helper.initPlantLevelImage();



    }

    public void onBtnMemoryListener(View target){
//        db = helper.getReadableDatabase();
//        String[] selectionArgs = {String.valueOf(Plant.typeOfPlant), String.valueOf(Plant.growthState)};
//        Cursor res = db.rawQuery("SELECT image_url FROM PlantLevelImage WHERE _id_plant=? AND level=?", selectionArgs);

        String filePath = "C:/study/3/3-2/Mobile/MobileTerm/Plant-Alarm/app/src/main/res/drawable/plant"+Plant.typeOfPlant+"_"+Plant.growthState+".jpg";
//        if(res.moveToFirst()){
//            int idx = res.getColumnIndex("image_url");
//            if(idx != -1){
////            String filePath = res.getString(idx);

//
//                // 날짜를 원하는 형식으로 포맷팅

//            helper.insertPlantMemory(Plant.typeOfPlant, filePath,Plant.getPlantNickname(), Plant.getServiveDate(), "추억 메시지", "123");}
//        }
        Date currentDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = formatter.format(currentDate);
        helper.insertPlantMemory(Plant.typeOfPlant,
                imageID[Plant.typeOfPlant][Plant.growthState],
                Plant.getPlantNickname(),
                Plant.getServiveDate(),
                ET_message.getText().toString(),
                formattedDate);
//        res.close();
//        db.close();
        Toast.makeText(plant_main.this, "추억이 저장되었습니다!", Toast.LENGTH_LONG).show();
    }
    public void plantDieListener(View target){
        Intent intent = new Intent(getApplicationContext(), plant_die.class);
        startActivity(intent);
    }

    public void plantAlbumListener(View target){
        Intent intent = new Intent(getApplicationContext(), plant_album.class);
        startActivity(intent);

    }

    public void onBtnGrowingListener(View target){
        Plant.addGrowing(1);
        updatePlantInfoText();
    }

    public void onBtnRestListener(View target){
        Plant.rest();
        updatePlantInfoText();
    }

    public void onBtnDieListener(View target){

        Intent intent = new Intent(getApplicationContext(), plant_die.class);
        startActivity(intent);
    }

    public void updatePlantInfoText(){
        String growingDate_str = "성장 "+Integer.toString(Plant.getGrowingDate())+"일 째";
        String survivieDate_str = "생존 "+Integer.toString(Plant.getServiveDate())+"일 째";
        TV_growingDate.setText(growingDate_str);
        TV_serviveDate.setText(survivieDate_str);
        TV_plantNickname.setText(Plant.getPlantNickname());


        if(Plant.getServiveDate()>3){
            IV_plantImage.setImageResource(R.drawable.plant0_1);
            Plant.growthState = 1;
        }
        if(Plant.getServiveDate()>6){
            IV_plantImage.setImageResource(R.drawable.plant0_2);
            Plant.growthState = 2;
        }
         if(Plant.getServiveDate()>9){

            IV_plantImage.setImageResource(R.drawable.plant0_3);
             Plant.growthState = 3;
        }
    }

    public void onBtnInsectListener(View target){

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    public void onBtnAlarmListener(View target){

        Intent intent = new Intent(getApplicationContext(), AlarmActivity.class);
        startActivity(intent);
    }


}