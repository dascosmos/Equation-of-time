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
        return count <= 0;

    }

    public void createDocument(String id,Object[]info){

        DBObject year = new BasicDBObject("_id", id);
        table.insert(year);

    }

    public void actualizeDocument(String id, Object[] info){
        DBObject newQuery = new BasicDBObject().append("$push",new BasicDBObject("params",info));
        table.update(new BasicDBObject("_id",id),newQuery);
    }

    public String fetchDocuments(String id) {
        DBCursor cursor = null;
        DBObject query = new BasicDBObject().append("_id",id);
        DBObject field = new BasicDBObject();
        String str = "";
        for (int i = 0; i < 365; i++){
            field.put("params", i);
            cursor = table.find(query,field);

        }
        while(cursor.hasNext()){
            BasicDBObject obj = (BasicDBObject) cursor.next();
            str=obj.getString("params");
        }
        return str;
    }
}
