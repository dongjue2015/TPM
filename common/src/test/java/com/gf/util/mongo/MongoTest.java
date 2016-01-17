package com.gf.util.mongo;

import org.bson.Document;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoTest {
  private static Logger log=LoggerFactory.getLogger(MongoTest.class);
  @Before
  public void setUp() throws Exception {}

  @After
  public void tearDown() throws Exception {}

//  @Test
  public void useMongo() {
    log.info("useMongo()");
    MongoClient mongoClient=new MongoClient("10.6.10.6",9000);
    MongoDatabase db=mongoClient.getDatabase("test2");
    MongoCollection<Document> collection=db.getCollection("t1");
    
    /* create batch
    List<Document> documents = new ArrayList<Document>();
    for (int i = 0; i < 100; i++) {
        documents.add(new Document("i", i));
    }
    System.out.println(collection.count());
    collection.insertMany(documents);
    System.out.println(collection.count());
    */
    
    /*Create a document
    Document doc = new Document("name", "MongoDB")
    .append("type", "database")
    .append("count", 1)
    .append("info", new Document("x", 203).append("y", 102));
    System.out.println(collection.count());
    collection.insertOne(doc);
    System.out.println(collection.count());
    */
    
    /* query
    System.out.println(collection.find().first().toJson());
    Document d=collection.find(eq("projectId","p2")).first();
    System.out.println(d==null?d:d.toJson());
    */
     
     /* query many
     MongoCursor<Document> cursor = collection.find().iterator();
     try {
     while (cursor.hasNext()) {
     System.out.println(cursor.next().toJson());
     }
     } finally {
     cursor.close();
     }
     */
    
    /* Use Block
     Block<Document> printBlock = new Block<Document>() {
       @Override
       public void apply(final Document document) {
           System.out.println(document.toJson());
       }
      };
      collection.find(gt("i", 50)).forEach(printBlock);
      */ 
    
    /* Projecting 
    System.out.println(collection.find().sort(ascending("i")).projection(excludeId()).first().toJson());
    */
    
    /*Update one 
    collection.updateOne(eq("i", 10), new Document("$set", new Document("i", 110)));
    */
    /*Update Many 
    UpdateResult updateResult = collection.updateMany(lt("i", 200),
        new Document("$inc", new Document("i", 100)));
      System.out.println(updateResult.getModifiedCount());
   */
    
    /*Delete one 
    collection.deleteOne(lt("i", 210));
    */
    /* Delete many
    DeleteResult deleteResult = collection.deleteMany(lte("i", 240));
    System.out.println(deleteResult.getDeletedCount());
    */
      
     mongoClient.close();
  }

  @Test
  public void adminMongo(){
    MongoClient mongoClient = new MongoClient("10.6.10.6",9000);
    MongoDatabase database = mongoClient.getDatabase("test");
    MongoCollection<Document> collection = database.getCollection("t");
    
    //list databaseNames
    for (String name: mongoClient.listDatabaseNames()) {
        System.out.println(name);
    }
    
    //drop a database by name
    mongoClient.getDatabase("test2").drop();
    
    /* drop a collection
    database.getCollection("cappedCollection").drop();
    */
    
    /* customize a creation of collection 
    database.createCollection("cappedCollection",
        new CreateCollectionOptions().capped(true).sizeInBytes(3l));
    */
    
    //get a list of collection names
    for (String name : database.listCollectionNames()) {
      System.out.println(name);
    }
    
    
 // create an ascending index on the "i" field
    collection.createIndex(new Document("i", 1));
    
    for (final Document index : collection.listIndexes()) {
      System.out.println(index.toJson());
  }
    
    Document buildInfo = database.runCommand(new Document("buildInfo", 1));
    System.out.println(buildInfo);
  }
}
