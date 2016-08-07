package pe.edu.upc.lindley.model;

/**
 * Created by lmoralea on 06/08/2016.
 */
public class ContactState {

    int idContactState;
    String contactState;

    public ContactState(int idContactState,String contactState){
        this.idContactState = idContactState;
        this.contactState = contactState;
    }

    public int getIdContactState() {
        return idContactState;
    }

    public void setIdContactState(int idContactState) {
        this.idContactState = idContactState;
    }

    public String getContactState() {
        return contactState;
    }

    public void setContactState(String contactState) {
        this.contactState = contactState;
    }
}
