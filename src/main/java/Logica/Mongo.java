package Logica;


import com.mongodb.*;

public class Mongo {

    MongoClient mongo;
    DB db;
    DBCollection table;
    public void createConnection(){

        mongo = new MongoClient("localhost",27017);
    }

    @Deprecated
    public void createCollection(){
        db = mongo.getDB("Astronomy");
        table = db.getCollection("searches");
    }

    public boolean validateDocument(String id) {

        DBObject query = new BasicDBObject();
        query.put("_id",id);
        long count =table.count(query);
        if (count > 0){
            return false;
        }else{
            return true;
        }

    }


    public void createDocument(String id,Object[]info){

            DBObject year = new BasicDBObject("_id", id);
            table.insert(year);

    }

    public void actualizeDocument(String id, Object[] info){
        DBObject newQuery = new BasicDBObject().append("$push",new BasicDBObject("params",info));
        table.update(new BasicDBObject("_id",id),newQuery);
    }

    public void fetchDocuments(String id){
        DBObject query = new BasicDBObject();
        query.put("_id",id);
        long count = table.find(query).count();
        System.out.println("Cuenta de tabla: "+count);
    }





}
