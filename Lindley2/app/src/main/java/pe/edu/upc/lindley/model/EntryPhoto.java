package pe.edu.upc.lindley.model;

import com.orm.SugarRecord;

import java.util.Date;
/**
 * Created by Ruth on 08/08/2016.
 */
public class EntryPhoto extends SugarRecord {

    private Date createdPht;
    private String nombreFoto;

    public EntryPhoto() {    }

    public Date getCreatedAt() {
        return createdPht;
    }

    public void setCreatedAt(Date CreatedAt) {
        this.createdPht = CreatedAt;
    }

    public String getNombreFoto () { return nombreFoto;   }
    public void setNombreFoto(String nombreFoto) {this.nombreFoto =nombreFoto;
    }
}
