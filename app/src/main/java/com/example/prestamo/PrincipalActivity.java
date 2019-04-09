package com.example.prestamo;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class PrincipalActivity extends AppCompatActivity {

    private static int NUSUARIO=7987;
    private static int NPRESTAMO=7988;
    private static int ACERCADE=7989;
    private static int MCLIENTES=7990;
    private static int MPRESTAMO=7991;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        TextView tv1= findViewById(R.id.tvRegistro);
        registerForContextMenu(tv1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()){
            case R.id.mnNuevoUsuario:
                intent=new Intent(this, MainActivity.class);
                startActivityForResult(intent, NUSUARIO);
                break;
            case R.id.mnNuevoPrestamo:
                intent =new Intent(this, SecondActivity.class);
                startActivityForResult(intent, NPRESTAMO);
                break;
            case R.id.mnAcercaDe:
                Toast.makeText(this, "Electiva Android", Toast.LENGTH_SHORT).show();
                break;
            case R.id.mnVerCliente:
                intent =new Intent(this, VerClienteActivity.class);
                startActivityForResult(intent, MCLIENTES);
                break;
            case R.id.mnVerPrestamo:
                intent =new Intent(this, VerPrestamosActivity.class);
                startActivityForResult(intent, MPRESTAMO);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        TextView tv=findViewById(R.id.tvRegistro);
        if(requestCode==NUSUARIO){
            if(resultCode==RESULT_OK){
                tv.append("\nIngreso de nuevo Cliente " + data.getStringExtra("nombre"));
            }else{
                tv.append("\nCancelo Ingreso de nuevo cliente");
            }
        }else if(requestCode==NPRESTAMO){
            if(resultCode==RESULT_OK){
                tv.append("\nIngreso de nuevo Prestamo");
            }else{
                tv.append("\nCancelo Ingreso de nuevo prestamo");
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.contextual,menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        TextView tv1 = findViewById(R.id.tvRegistro);
        if(item.getItemId()==R.id.mncCopiar){
            ClipboardManager clipboardManager = (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);
            ClipData clipData = ClipData.newPlainText("Historial",tv1.getText().toString());
            clipboardManager.setPrimaryClip(clipData);
        }else{
            tv1.setText("");
        }
        return super.onContextItemSelected(item);
    }
}
