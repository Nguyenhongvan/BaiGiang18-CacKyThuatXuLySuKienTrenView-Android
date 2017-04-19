package com.vansuzy.baigiang18_cackythuatxulysukientrenview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnLongClickListener {
    EditText txtA, txtB;
    Button btnTru;
    Button btnNhan, btnChia;
    Button btnAn;
    Button btnThoat;
    View.OnClickListener suKienChiaSe = null;   // biến có khả năng sinh sự kiện

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControls();
        addEvents();
    }

    private void addEvents() {
        // Anomous listener
        btnTru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyPhepTru();
            }
        });

        // Variable as Listener
        suKienChiaSe = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.btnNhan) {  // getId(): trả về id của control mà đang sử dụng sự kiện này
                    xuLyPhepNhan();
                } else if (v.getId() == R.id.btnChia) {
                    xuLyPhepChia();
                }
            }
        };
        btnNhan.setOnClickListener(suKienChiaSe);
        btnChia.setOnClickListener(suKienChiaSe);

        // Activity as Listener
        btnAn.setOnLongClickListener(this); // nhấn thật lâu (trên 1.5s) thì sẽ phát sinh sự kiện ẩn btnAn

        // Explicit as Listener
        btnThoat.setOnClickListener(new MyEvent());
    }

    private void xuLyPhepChia() {
        int a = Integer.parseInt(txtA.getText().toString());
        int b = Integer.parseInt(txtB.getText().toString());
        int c = a/b;
        Toast.makeText(MainActivity.this, "Chia = " + c, Toast.LENGTH_LONG).show();
    }

    private void xuLyPhepNhan() {
    }

    private void xuLyPhepTru() {
    }

    // addControls() là hàm dùng để khởi tạo các control
    private void addControls() {
        txtA = (EditText) findViewById(R.id.txtA);
        txtB = (EditText) findViewById(R.id.txtB);
        btnTru = (Button) findViewById(R.id.btnTru);
        btnNhan = (Button) findViewById(R.id.btnNhan);
        btnChia = (Button) findViewById(R.id.btnChia);
        btnAn = (Button) findViewById(R.id.btnAn);
        btnThoat = (Button) findViewById(R.id.btnThoat);
    }

    // onClickXML
    public void xuLyPhepCong(View view) {
    }

    @Override
    public boolean onLongClick(View v) {
        if (v.getId() == R.id.btnAn) {
            btnAn.setVisibility(View.INVISIBLE);
        }
        return false;
    }

    public class MyEvent implements View.OnClickListener, View.OnLongClickListener {

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.btnThoat) {
                finish();
            }
        }

        @Override
        public boolean onLongClick(View v) {
            return false;
        }
    }

    // View Subclassing: khi ấn vào button btnDoiManHinhKhac thì sẽ đổi sang màn hình có button có nội dung là Quay Về, và khi ấn vào button này thì sẽ quay trở về màn hình chính.
    public void xuLyDoiManHinhKhac(View view) {
        Button btnMoi = new Button(MainActivity.this) {
            @Override
            public boolean performClick() {
                setContentView(R.layout.activity_main);
                addControls();
                addEvents();
                return super.performClick();
            }
        };
        btnMoi.setText("Quay Về");
        btnMoi.setWidth(200);
        btnMoi.setHeight(200);
        setContentView(btnMoi);
    }
}
