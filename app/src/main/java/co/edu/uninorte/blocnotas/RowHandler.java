package co.edu.uninorte.blocnotas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fdjvf on 3/13/2017.
 */

public class RowHandler extends BaseAdapter {

        public ArrayList<FileArch> Data;
        private Context MyContext;


        public RowHandler(Context context, ArrayList<FileArch> data) {

            MyContext = context;
            Data = data;
        }

        @Override
        public int getCount() {
            return Data.size();
        }

        @Override
        public Object getItem(int position) {
            return Data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            String Title = Data.get(position).Title;
            String Date=Data.get(position).CreationDate.toString();
            long Id=Data.get(position).getId();
           // String Info=Data.get(position).Content;
            if (convertView == null) {
                LayoutInflater T = (LayoutInflater) MyContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = T.inflate(R.layout.myrow, null);
            }
            //Ingresamos el listviw con cada una de las propiedades y deseadas y posteriormente devolvermos esa view( Cada view es una fila)
            TextView titleView = (TextView) convertView.findViewById(R.id.Title);
            titleView.setText(Title);
            TextView dateView = (TextView) convertView.findViewById(R.id.Date);
            dateView.setText(Date);
            Button MyButton = (Button) convertView.findViewById(R.id.delFile);
            MyButton.setTag(Id);
            MyButton.setFocusable(false);
            MyButton.setFocusableInTouchMode(false);


            return convertView;
        }
    }


