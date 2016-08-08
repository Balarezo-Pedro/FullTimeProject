package pe.edu.upc.lindley;

import pe.edu.upc.lindley.model.PhotoService;

/**
 * Created by Ruth on 08/08/2016.
 */
public class Lindley2Aplication extends com.orm.SugarApp{

        private PhotoService  photoService = new PhotoService();

        public PhotoService getPhotoService() {
            return photoService;
        }

        public void setPhotoService (PhotoService  photoService) {
            this.photoService=photoService;
        }
}
