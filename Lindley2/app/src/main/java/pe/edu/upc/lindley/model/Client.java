package pe.edu.upc.lindley.model;

/**
 * Created by lmoralea on 05/08/2016.
 */
public class Client {
    int idClient;
    String name;
    String legalName;

    public Client(int idClient,String name,String legalName){
        this.idClient=idClient;
        this.name=name;
        this.legalName=legalName;
    }


    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLegalName() {
        return legalName;
    }

    public void setLegalName(String legalName) {
        this.legalName = legalName;
    }
}
