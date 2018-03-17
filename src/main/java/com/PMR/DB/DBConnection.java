package com.PMR.DB;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class DBConnection {

	private MongoClient mongoClient;
	private MongoDatabase database;
	private MongoCollection<Document> mongoCollection;

	public void MakeConnection(String DBName) {
		MongoClientURI uri = new MongoClientURI(
				"mongodb://smogili:Winter17@pmr-shard-00-00-x0hbe.mongodb.net:27017,pmr-shard-00-01-x0hbe.mongodb.net:27017,pmr-shard-00-02-x0hbe.mongodb.net:27017/test?ssl=true&replicaSet=PMR-shard-0&authSource=admin");
		mongoClient = new MongoClient(uri);
		database = mongoClient.getDatabase(DBName);
	}

	public void AddData(String CollectionName, Map<String, Object> data) {
		mongoCollection = database.getCollection(CollectionName);
		Document document = new Document();
		document.putAll(data);
		mongoCollection.insertOne(document);
	}

	public List<Document> findData(String CollectionName, String key, String Value) {
		List<Document> items = new ArrayList<Document>();
		mongoCollection = database.getCollection(CollectionName);
		mongoCollection.find(Filters.eq(key, Value)).into(items);
		return items;
	}

	public List<Document> MultipleQueryfindData(String CollectionName, String key, String Value, String key1,
			String Value2) {
		List<Document> items = new ArrayList<Document>();
		mongoCollection = database.getCollection(CollectionName);
		Bson filter = Filters.and(Filters.eq(key, Value), Filters.eq(key1, Value2));
		mongoCollection.find(filter).into(items);
		return items;
	}

	public void closeConnection() {
		mongoClient.close();
	}

	public void UpdateData(String CollectionName, String key, Object Value, String Searchkey, Object SearchValue) {
		mongoCollection = database.getCollection(CollectionName);
		BasicDBObject newDocument = new BasicDBObject();
		newDocument.append("$set", new BasicDBObject().append(key, Value));
		BasicDBObject searchQuery = new BasicDBObject().append(Searchkey, SearchValue);
		mongoCollection.updateOne(searchQuery, newDocument);
	}
}
