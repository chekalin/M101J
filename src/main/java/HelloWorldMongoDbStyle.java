import com.mongodb.*;

import java.net.UnknownHostException;

public class HelloWorldMongoDbStyle {

    public static void main(String[] args) throws UnknownHostException {
        MongoClient client = new MongoClient(new ServerAddress("localhost", 27017));
        final DB database = client.getDB("course");
        final DBCollection collection = database.getCollection("hello");
        final DBObject document = collection.findOne();
        System.out.println(document);
    }
}
