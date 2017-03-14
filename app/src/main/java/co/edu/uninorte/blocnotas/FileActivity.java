package co.edu.uninorte.blocnotas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import co.edu.uninorte.blocnotas.R;

public class FileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);
        Intent Te=getIntent();
        Bundle MyBundle= Te.getExtras();
         String Title=MyBundle.getString("Title");

       String Text=MyBundle.getString("Text");
        TextView Tit=(TextView)findViewById(R.id.TitleTbx);
        EditText TextTbx=(EditText)findViewById(R.id.TextFile);
        Tit.setText(Title);
        TextTbx.setText(Text);
    }

    public void SaveFile(View view) {


        EditText Temp= (EditText)findViewById(R.id.TextFile);
        Intent T = getIntent();
        String Data=Temp.getText().toString();
        T.putExtra("TextChanged",Data);
        setResult(RESULT_OK,T);
        finish();

    }
}
