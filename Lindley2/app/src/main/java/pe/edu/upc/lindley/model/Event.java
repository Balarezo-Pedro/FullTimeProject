package pe.edu.upc.lindley.model;

import java.util.Date;

/**
 * Created by lmoralea on 31/07/2016.
 */
public class Event {


    String idEvent;
    String idContact;
    String idEventState;
    String createdAt;
    String comment;

    public Event(String idEvent,String idContact,String idEventState,String date, String comment){
        this.idEvent=idEvent;
        this.idContact=idContact;
        this.idEventState=idEventState;
        this.createdAt = date;
        this.comment = comment;
    }
}
