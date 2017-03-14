package co.edu.uninorte.blocnotas;

import com.orm.SugarRecord;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by fdjvf on 3/13/2017.
 */

public class FileArch extends SugarRecord {


        public String Title;
        public String Content;
        public String CreationDate;

     public  FileArch()
     {

     }
        public FileArch(String title, String content,String date){

            Title=title;
            Content=content;
            CreationDate=date;
        }

}
