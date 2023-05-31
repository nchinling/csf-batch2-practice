package ibf2022.batch2.csf.backend.repositories;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ArchiveRepository {

	@Autowired
    MongoTemplate mongoTemplate;

	//TODO: Task 4
	// You are free to change the parameter and the return type
	// Do not change the method's name
	// Write the native mongo query that you will be using in this method
	//
	//
	
    /*
	 * db.archives.insert({
	 * 	_id: "bundleId",
	 *  date: "date",
	 *  title: "title",
	 * 	name: "name",
	 * 	comments: "comments",
	 *  urls: "urls",
	 * })
	 */
	public Object recordBundle(String title, String name, String comments, List<String> urls) {
		String bundleId = UUID.randomUUID().toString().substring(0, 8);
		String date = new Date().toString();
		Document doc = new Document();
		doc.put("_id", bundleId);
		doc.put("date", date);
		doc.put("title", title);
		doc.put("name", name);
        doc.put("comments", comments);
		doc.put("urls", urls);
		mongoTemplate.insert(doc, "archives");
		return (Object)bundleId;
	}

	//TODO: Task 5
	// You are free to change the parameter and the return type
	// Do not change the method's name
	// Write the native mongo query that you will be using in this method
	//
	//
	public Object getBundleByBundleId(/* any number of parameters here */) {
		return null;
	}

	//TODO: Task 6
	// You are free to change the parameter and the return type
	// Do not change the method's name
	// Write the native mongo query that you will be using in this method
	//
	//
	public Object getBundles(/* any number of parameters here */) {
		return null;
	}


}
