package restAPI;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
public enum Endpoints {


    CONTACTS("Can create get and delete a contact give the right parameters","/contacts/my");

    String summary;
    String path;

    Endpoints(String summary, String path){
        this.summary = summary;
        this.path = path;
    }

    public String getSummary(){
        return this.summary;
    }

    public String getPath(){
        return this.path;
    }
}