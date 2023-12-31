//package com.example.plantalarm;
//
//import androidx.appcompat.app.AlertDialog;
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.ContentValues;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.AbsListView;
//import android.widget.AdapterView;
//import android.widget.Button;
//import android.widget.ListView;
//import android.widget.Switch;
//import android.widget.Toast;
//
//public class AlarmActivity extends AppCompatActivity {
//    //MyData 클래스의 생성자에 알람설정을 전달히여 객체를 만들되 이들의 배열을 준비해둠
//    // ---------- 데이터베이스 부분--------- //
//    plantDB helper;
//    SQLiteDatabase db;
//
//    //--------------------------------------//
//    private MyData[] mData = {
////            new MyData("오전 09시 30분", new String[]{"화", "금"},true),
//            new MyData(9, 30, new String[]{"화", "금"}, 1, 1, 1)
//    };
//
//
//
//    private ListView mList;
//    private MyAdapter mAdapter;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
////        setContentView(R.layout.activity_main);
//        setContentView(R.layout.activity_alram);
//
//
//        // --------------- 데이터베이스 추가 -------------- //
//        //
//        helper = new plantDB(this);
//        db = helper.getWritableDatabase(); //필요할 때마다 db를 통해서 SQL 문장을 실행하면 된다.
//
//        // Alarm 초기 데이터 저장하기
//        helper.insertAlarm(9,30,"1",1,1,1,1,1,1,30);
//        helper.insertAlarm(9,35,"1,2",1,1,1,1,1,1,30);
//        helper.insertAlarm(9,40,"1,2,3",1,1,1,1,1,1,30);
//        helper.insertAlarm(9,45,"1,2,3",1,1,1,1,1,1,30);
//        helper.insertAlarm(9,50,"1,2,3",1,1,1,1,1,1,30);
//        helper.insertAlarm(10,0,"1,2,3",1,1,1,1,1,1,30);
//        helper.insertAlarm(11,0,"1,2,3",1,1,1,1,1,1,30);
//        helper.insertAlarm(12,0,"1,2,3",1,1,1,1,1,1,30);
//        helper.insertAlarm(13,0,"1,2,3",1,1,1,1,1,1,30);
//
//        db.close();
//        // ---------------------------------------- //
//
//        //------------------------------리스트뷰 ---------------------------------------------//
//        //리스트뷰 초기화
//        //(리스트뷰 초기화)
//        mList = (ListView) findViewById(R.id.alramList); //아까 설정한 알람리스트
//        //MyAdapter 클래스의 생성자에 컨텍스트와 배열을 전달하여 커스텀 어댑터를 만들고
//        mAdapter = new MyAdapter(this, mData); // 컨텍스트(ctx), mData(넣을 데이터)
//        // setAdapter()를 호출하여 어댑터뷰와 연결한다.
//        mList.setAdapter(mAdapter);
//
//        //항목 한개 선택모드
//        mList.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE); // 선택모드 변경 : 항목 한개를 선택
//
//
//// 클릭 이벤트 처리
//        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
////                Log.d("ItemClick", "Position: " + position);
//                Intent intent = new Intent(AlarmActivity.this, AlarmSetActivity.class); //버튼이 클릭되면 AlaramSetActivity 시작
//                startActivity(intent); //AlarmSetActivity를 인수로 준 인텐트 객체 생성 (명시적 인텐트)
//
////                Toast.makeText(AlarmActivity.this, mData[position].time + " 선택!", Toast.LENGTH_LONG).show();
//
//            }
//        });
//
//        // 긴 클릭시 : 다이얼로그 띄운 뒤 삭제 이벤트 처리
//        mList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                showDeleteDialog(view , position); // 삭제 다이얼로그 표시
//
//                return false;
//            }
//        });
//
//
////        ////-----리스트뷰 내 스위치------/////
////        Switch switchView; // '스위치' 버튼 클릭시
////        switchView = (Switch) findViewById(R.id.alramCheck);
////        switchView.setOnClickListener(new View.OnClickListener(){
////            public void onClick(View v){
////                Toast.makeText(AlarmActivity.this, "스위치 선택", Toast.LENGTH_LONG).show();
////            }
////        });
//
//        //--------------------------------------------------------------------------------------//
//
//        //------------------------------ 알람 추가 ---------------------------------------------//
//        Button btn_addAlarm = (Button) findViewById(R.id.btn_addAlarm); //'+' 버튼 클릭시
//        btn_addAlarm.setOnClickListener(new View.OnClickListener(){
//            public void onClick(View v){
////                showDeleteDialog(v , 1);
////                mData.add("")
//                selectAlarmList(); // 알람 리스트 출력
//
//                Intent intent = new Intent(AlarmActivity.this, AlarmSetActivity.class); //버튼이 클릭되면 AlaramSetActivity 시작
//                startActivity(intent); //AlarmSetActivity를 인수로 준 인텐트 객체 생성 (명시적 인텐트)
//            }
//        });
//
//        //--------------------------------------------------------------------------------------//
//
//    }
//
//    // ------------------------------데이터베이스 알람 리스트 결과 조회 --------------- //
//    public void selectAlarmList(){
//        Cursor cursor;
//        db = helper.getReadableDatabase(); //필요할 때마다 db를 통해서 SQL 문장을 실행하면 된다.
//        cursor = db.rawQuery("SELECT  _id_alarm, hour, minutes, week, alarm_check FROM Alarm", null); // 알람리스트 sql로 받아오기
//
//        String s = "_id_alarm, hour, minutes, week, alarm_check\n";
//        while (cursor.moveToNext()){
//            s+= cursor.getString(0) + " ";
//            s+= cursor.getString(1) + " ";
//            s+= cursor.getString(2) + " ";
//            s+= cursor.getString(3) + " ";
//            s+= cursor.getString(4) + " \n";
//        }
//        System.out.println(s);
//
//        db.close();
//    }
//
//    // -------------------------------------------------------------------------- //
//
//    //------------------------------ 알람 삭제 ---------------------------------------------//
//    // 챕터 7 : AlertDialog
//    private void showDeleteDialog(View view, int position){ //뷰, 삭제할 리스트뷰 (알람)의 index
//        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
//        alertDialogBuilder.setMessage("알람을 삭제하시겠습니까?");
//        // "예"를 클릭한 경우
//        alertDialogBuilder.setPositiveButton("예", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                Toast.makeText(AlarmActivity.this, "알람이 삭제되었습니다.", Toast.LENGTH_LONG).show();
//            }
//        });
//
//        // "아니오"를 클릭한 경우
//        alertDialogBuilder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                Toast.makeText(AlarmActivity.this, "취소되었습니다", Toast.LENGTH_LONG).show();
////                finish();
//            }
//        });
//
//        AlertDialog alertDialog = alertDialogBuilder.create(); // AlertDialog 객체 생성
//        alertDialog.show(); //다이얼로그 화면에 표시.
//
//    }
//
//    //--------------------------------------------------------------------------------------//
//
//
//    // ------------------------------------ 제일 아래 네비바 ----------------------------------//
//    // '식물' 버튼 클릭시, '식물'페이지로 이동
//
//    public void onBtnPlantListener(View target){
//
//        Intent intent = new Intent(getApplicationContext(), plant_main.class);
//        startActivity(intent);
//
//    }
//    // -------------------------------------------------------------------------------------//
//}
//
//////////////////////////////////////////////////////////////////////


package com.example.plantalarm;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;

public class AlarmActivity extends AppCompatActivity {
    //MyData 클래스의 생성자에 알람설정을 전달히여 객체를 만들되 이들의 배열을 준비해둠
    // ---------- 데이터베이스 부분--------- //
    plantDB helper;
    SQLiteDatabase db;

    //--------------------------------------//

    public static ArrayList<MyData> mDataList = new ArrayList<>();




    private ListView mList;
    private MyAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_alram);


        // --------------- 데이터베이스 추가 -------------- //
        //
        helper = new plantDB(this);
        db = helper.getWritableDatabase(); //필요할 때마다 db를 통해서 SQL 문장을 실행하면 된다.

        // Alarm 초기 데이터 저장하기
        helper.insertAlarm(9,30,"월",1,1,1,1,1,1,30);
        helper.insertAlarm(9,35,"수, 금",1,1,1,1,1,1,30);
        helper.insertAlarm(9,40,"월, 화",1,1,1,1,1,1,30);
        helper.insertAlarm(10,30,"목",1,1,1,1,1,2,30);
        helper.insertAlarm(11,35,"토, ",1,1,1,1,1,1,30);
        helper.insertAlarm(11,40,"월, 금",1,1,1,1,1,0,30);

        //                                                                            5 : soundCheck, 6 : plantMissionCheck, repeatCount

        db.close();
        // ---------------------------------------- //


        // -------------- mDataList 초기값 추가 ----------- //
        initAlarmList(); // 데이터 추가
        // ---------------------------------------- //

        //------------------------------리스트뷰 ---------------------------------------------//
        //리스트뷰 초기화
        //(리스트뷰 초기화)
        mList = (ListView) findViewById(R.id.alramList); //아까 설정한 알람리스트
        //MyAdapter 클래스의 생성자에 컨텍스트와 배열을 전달하여 커스텀 어댑터를 만들고
        mAdapter = new MyAdapter(this, mDataList); // 컨텍스트(ctx), mData(넣을 데이터)
        // setAdapter()를 호출하여 어댑터뷰와 연결한다.
        mList.setAdapter(mAdapter);

        //항목 한개 선택모드
        mList.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE); // 선택모드 변경 : 항목 한개를 선택


// 클릭 이벤트 처리
        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Log.d("ItemClick", "Position: " + position);
                Intent intent = new Intent(AlarmActivity.this, AlarmEditActivity.class); //버튼이 클릭되면 수정하는 곳으로 가도록 한다.
                startActivity(intent); //AlarmSetActivity를 인수로 준 인텐트 객체 생성 (명시적 인텐트)
                Edit._id_alarm = position;
//                Toast.makeText(AlarmActivity.this, mData[position].time + " 선택!", Toast.LENGTH_LONG).show();

            }
        });

        // 긴 클릭시 : 다이얼로그 띄운 뒤 삭제 이벤트 처리
        mList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                showDeleteDialog(view , position); // 삭제 다이얼로그 표시

                return false;
            }
        });


//        ////-----리스트뷰 내 스위치------/////
//        Switch switchView; // '스위치' 버튼 클릭시
//        switchView = (Switch) findViewById(R.id.alramCheck);
//        switchView.setOnClickListener(new View.OnClickListener(){
//            public void onClick(View v){
//                Toast.makeText(AlarmActivity.this, "스위치 선택", Toast.LENGTH_LONG).show();
//            }
//        });

        //--------------------------------------------------------------------------------------//

        //------------------------------ 알람 추가 버튼 ---------------------------------------------//
        Button btn_addAlarm = (Button) findViewById(R.id.btn_addAlarm); //'+' 버튼 클릭시
        btn_addAlarm.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
//                showDeleteDialog(v , 1);
//                mData.add("")
                selectAlarmList(); // 알람 리스트 출력


                Intent intent = new Intent(AlarmActivity.this, AlarmSetActivity.class); //버튼이 클릭되면 AlaramSetActivity 시작
                startActivity(intent); //AlarmSetActivity를 인수로 준 인텐트 객체 생성 (명시적 인텐트)
            }
        });

        //--------------------------------------------------------------------------------------//

    }


    // ----------------------------- 알람 추가 함수 -------------------------------- //
    // 시, 분, 알림음 설정, 반복 횟수, 식물 미션 참여
    public void addAlarm(int hour,int  minutes, boolean sound_check, int repeat_count, boolean plant_mission_check){

        helper = new plantDB(this);
        db = helper.getWritableDatabase(); // write 모드로 db 열기 //필요할 때마다 db를 통해서 SQL 문장을 실행하면 된다.
        int sound_check_sql = sound_check ? 1 : 0; // bool을 int로 바꾸기
        int plant_mission_check_sql = plant_mission_check ? 1 : 0; // bool을 int로 바꾸기
        int alarm_check = 1; //알람 여부
        // true -> 정수로 바꿔주기
        String week = "월,화";
        String sql = "INSERT INTO Alarm(hour, minutes, week, alarm_check, sound_check, repeat_count, plant_mission_check) VALUES (?,?,?,?,?,?,?)";

        // 쿼리 실행
        db.execSQL(sql, new Object[]{hour, minutes, week, alarm_check, sound_check_sql, repeat_count, plant_mission_check_sql});

        Cursor cursor = db.rawQuery("SELECT _id_alarm FROM Alarm ORDER BY _id_alarm DESC LIMIT 1", null); //제일 마지막 id 얻기
        int _id_alarm = cursor.getInt(0);
        // mDataList에 데이터 추가
        mDataList.add(new MyData(_id_alarm,hour, minutes,"월,화", alarm_check, sound_check_sql, repeat_count, plant_mission_check_sql));


        // 추가된 데이터를 어댑터에 알리고 UI 갱신
        mAdapter.notifyDataSetChanged();


        // 토스트 메시지 표시
        Toast.makeText(AlarmActivity.this, "알람이 추가되었습니다.", Toast.LENGTH_LONG).show();

        // 데이터베이스 닫기
        db.close();

    }

    public void updateList(){
        mAdapter.notifyDataSetChanged();
    }

    // --------------------------------------------------------------------------- //


    // ------------------------------데이터베이스 알람 리스트 결과 조회 --------------- //
    public void selectAlarmList(){
        Cursor cursor;
        db = helper.getReadableDatabase(); //필요할 때마다 db를 통해서 SQL 문장을 실행하면 된다.
        cursor = db.rawQuery("SELECT  _id_alarm, hour, minutes, week, alarm_check FROM Alarm", null); // 알람리스트 sql로 받아오기


        String s = "_id_alarm, hour, minutes, week, alarm_check, sound_check,plant_mission_check\n";

        while (cursor.moveToNext()){
            s+= cursor.getString(0) + " ";
            s+= cursor.getString(1) + " ";
            s+= cursor.getString(2) + " ";
            s+= cursor.getString(3) + " ";

            s+= cursor.getString(4) + " ";
            s+= cursor.getString(5) + " ";
            s+= cursor.getString(6) + " \n";

        }
        System.out.println(s);

        db.close();
    }

    // -------------------------------------------------------------------------- //


    // ------------------------------데이터베이스 알람 리스트 결과 조회 --------------- //
    public void initAlarmList(){
        Cursor cursor;
        db = helper.getReadableDatabase(); //필요할 때마다 db를 통해서 SQL 문장을 실행하면 된다.
        cursor = db.rawQuery("SELECT  _id_alarm, hour, minutes, week, alarm_check, sound_check, repeat_count, plant_mission_check  FROM Alarm", null); // 알람리스트 sql로 받아오기

        String s = "_id_alarm, hour, minutes, week, alarm_check, sound_check, repeat_count, plant_mission_check\n";
        while (cursor.moveToNext()){
//            s+= cursor.getString(0) + " ";
//            s+= cursor.getString(1) + " ";
//            s+= cursor.getString(2) + " ";
//            s+= cursor.getString(3) + " ";
//            s+= cursor.getString(4) + " ";
//            s+= cursor.getString(5) + " ";
//            s+= cursor.getString(6) + " ";
//            s+= cursor.getString(7) + " \n";
            mDataList.add(new MyData(cursor.getInt(0), cursor.getInt(1),cursor.getInt(2) , cursor.getString(3), cursor.getInt(4),cursor.getInt(5),cursor.getInt(6),cursor.getInt(7)));
        }
        System.out.println(s);

        db.close();
    }

    // -------------------------------------------------------------------------- //



    //------------------------------ 알람 삭제 ---------------------------------------------//
    // 챕터 7 : AlertDialog
    private void showDeleteDialog(View view, int position){ //뷰, 삭제할 리스트뷰 (알람)의 index
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("알람을 삭제하시겠습니까?");
        // "예"를 클릭한 경우
        alertDialogBuilder.setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // 선택한 알람의 데이터 가져오기
                MyData selectedAlarm = mDataList.get(position);

                // plantDB 클래스의 deleteAlarm 메서드 호출하여 알람 삭제
//                helper.deleteAlarm(selectedAlarm.getAlarmId());


                db = helper.getReadableDatabase(); //필요할 때마다 db를 통해서 SQL 문장을 실행하면 된다.
                int dbIndex = position+1; //db에서는 1부터 시작하는 index.
                db.execSQL("DELETE FROM Alarm WHERE _id_alarm = "+dbIndex); // 알람리스트 sql로 삭제

                // 삭제된 알람을 mDataList에서도 삭제
                mDataList.remove(position);

                // 어댑터에 데이터가 변경되었음을 알리고 UI 갱신
                mAdapter.notifyDataSetChanged();

                Toast.makeText(AlarmActivity.this, "알람이 삭제되었습니다.", Toast.LENGTH_LONG).show();
            }
        });

        // "아니오"를 클릭한 경우
        alertDialogBuilder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(AlarmActivity.this, "취소되었습니다", Toast.LENGTH_LONG).show();
//                finish();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create(); // AlertDialog 객체 생성
        alertDialog.show(); //다이얼로그 화면에 표시.

    }

    //--------------------------------------------------------------------------------------//


    // ------------------------------------ 제일 아래 네비바 ----------------------------------//
    // '식물' 버튼 클릭시, '식물'페이지로 이동

    public void onBtnPlantListener(View target){
        // ==== 연경 ====
        if(Plant.isDead == true){
            Intent intent = new Intent(getApplicationContext(), plant_die.class);
            startActivity(intent);
        }
        else{

            Intent intent = new Intent(getApplicationContext(), plant_main.class);
            startActivity(intent);
        }

    }
    // -------------------------------------------------------------------------------------//
}

////////////////////////////////////////////////////////////////////
