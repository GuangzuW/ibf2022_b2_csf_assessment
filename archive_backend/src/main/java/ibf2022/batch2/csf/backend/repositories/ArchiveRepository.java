package ibf2022.batch2.csf.backend.repositories;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import ibf2022.batch2.csf.backend.models.document;

@Repository
public class ArchiveRepository {

	@Autowired
	private MongoTemplate template;

	static final String COLLECTION = "archives";

	public void insertComment(document d ){
        this.template.insert(d);
    }
	//TODO: Task 4
	// You are free to change the parameter and the return type
	// Do not change the method's name
	// Write the native mongo query that you will be using in this method
	//
	//
	public void recordBundle(String name, String title, String comments, List<String> urls) {
		document d = new document();
		d.setName(name);
		d.setTitle(title);
		d.setComments(comments);
		d.setUrl(urls.toArray(new String[urls.size()]));
		LocalDateTime date = LocalDateTime.now();
		d.setDate(date.toString());
		String bundleID = UUID.randomUUID().toString().substring(0, 8);
		d.setBundleId(bundleID);
		this.template.insert(d, COLLECTION);
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
