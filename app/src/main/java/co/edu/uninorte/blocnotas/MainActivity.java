package co.edu.uninorte.blocnotas;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.LabeledIntent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    String Title;
    ArrayList<FileArch> MyElements=new ArrayList<>();
    ListView MyListView;
    RowHandler rowhandler;
    FileArch FileStartAct;//Es el File que inicia la actividad y recibe los resultados
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FileArch.deleteAll(FileArch.class);




        MyListView=(ListView) findViewById(R.id.MyListView);
        rowhandler = new RowHandler(this, MyElements);
        MyListView.setAdapter(rowhandler);
        MyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                StarAct(position);
            }
        });

    }
    private void StarAct(int pos)
    {
        Intent intent = new Intent(this,FileActivity.class);
        Bundle MyBundle=new Bundle();
        FileStartAct = MyElements.get(pos);

        MyBundle.putString("Title",FileStartAct.Title);
        MyBundle.putString("Date",FileStartAct.CreationDate.toString());
        MyBundle.putString("Text",FileStartAct.Content);
        intent.putExtras(MyBundle);
        startActivityForResult(intent,1);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {

           String Text=data.getStringExtra("TextChanged");
            FileStartAct.Content=Text;
            FileStartAct.save();
           //Buscar el archivo que recien se modifico


//La actividad devolvio un ok y se procece normalmente
        } else if (requestCode == RESULT_CANCELED) {
//Probablemente la activiades recibir un error y se cerro
        }


    }

    public void AddFileAction(View view) {

        Calendar c= Calendar.getInstance();
        SimpleDateFormat df=new SimpleDateFormat("dd-MMMM-yyyy");
        final String Date=df.format(c.getTime());
       AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Insertar Titulo");
// Set up the input
        final EditText input = new EditText(this);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);
// Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Title = input.getText().toString();
                FileArch file=new FileArch(Title,"",Date);
                file.save();
                long t=file.getId();
                MyElements=new ArrayList<FileArch>(FileArch.listAll(FileArch.class));
                rowhandler.Data=MyElements;
                MyListView.setAdapter(rowhandler);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
        return;
    }

    public void DeleteFile(View view) {
        Button Bton=(Button)findViewById(R.id.delFile);
        long Id=Integer.parseInt(Bton.getTag().toString());
        FileArch temp = FileArch.findById(FileArch.class, Id);
        temp.delete();
        MyElements=new ArrayList<FileArch>(FileArch.listAll(FileArch.class));
        rowhandler.Data=MyElements;
        MyListView.setAdapter(rowhandler);
    }
}