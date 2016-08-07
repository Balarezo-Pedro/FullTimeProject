package pe.edu.upc.lindley.model;

import java.util.Date;

/**
 * Created by lmoralea on 05/08/2016.
 */
public class Contact {

    int idContact;
    Client client;
    String createdAt;
    ContactState contactState;
    String comment;
    String type;

    public Contact ( int idContact,Client client, String createdAt, ContactState contactState, String comment,String type){
        this.idContact = idContact;
        this.client = client;
        this.createdAt = createdAt;
        this.contactState = contactState;
        this.comment = comment;
        this.type=type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getIdContact() {
        return idContact;
    }

    public void setIdContact(int idContact) {
        this.idContact = idContact;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public ContactState getContactState() {
        return contactState;
    }

    public void setContactState(ContactState contactState) {
        this.contactState = contactState;
    }
}

