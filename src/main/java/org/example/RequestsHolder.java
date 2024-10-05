package org.example;

import java.util.ArrayList;
import java.util.List;

public class RequestsHolder {
    public static List<Request> cereriAdmini = new ArrayList<Request>();
    public static List<Request> getRequests(){
        return cereriAdmini;
    }

    public void addRequest(Request r){
        cereriAdmini.add(r);
    }
    public void removeRequest(Request r){
        cereriAdmini.remove(r);
    }
}
