package pe.edu.upc.lindley.model;


import android.database.sqlite.SQLiteDatabase;

import com.orm.SugarContext;
import com.orm.SugarDb;

import java.lang.reflect.Field;
import java.util.Date;

/**
 * Created by Ruth on 08/08/2016.
 */
public class PhotoService {
    public boolean addPhoto(String nombreFoto, String comentario) {
        EntryPhoto entryPhoto = new EntryPhoto();
        entryPhoto.setCreatedAt(new Date());
        entryPhoto.setNombreFoto(nombreFoto);
        entryPhoto.setComentario(comentario);
        return entryPhoto.save() > 0;
    }

    private SQLiteDatabase getDatabase() {
        try {
            Field f = SugarContext.getSugarContext().getClass().getDeclaredField("sugarDb");
            f.setAccessible(true);
            return ((SugarDb) f.get(SugarContext.getSugarContext())).getDB();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

}
