package ibf2022.batch2.csf.backend.repositories;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;

@Repository
public class ImageRepository {

	@Autowired
    private AmazonS3 s3;

	//TODO: Task 3
	// You are free to change the parameter and the return type
	// Do not change the method's name
	public Object upload(String name, String title, String comments, MultipartFile file) throws IOException {
		  // Add custom metadata
		  Map<String, String> userData = new HashMap<>();
		  userData.put("name", name);
		  userData.put("filename", file.getOriginalFilename());
		  userData.put("upload-date", (new Date()).toString());
  
		// Unzip the file
		ZipInputStream zipInputStream = new ZipInputStream(file.getInputStream());
		ZipEntry entry;
		List<String> uploadedUrls = new ArrayList<>();

		while ((entry = zipInputStream.getNextEntry()) != null) {
			if (!entry.isDirectory()) {
				// Get the entry's name and create a unique key
				String entryName = entry.getName();
				
        //Generate random key
        String genKey = UUID.randomUUID().toString().substring(0,8);

        // Generate a random key name, attach to image folder
        String key = "images/"+genKey+"/"+entryName;
		// String key = genKey+"/"+entryName;

		
				// Read the entry's content
				byte[] entryContent = zipInputStream.readAllBytes();
	
				// Add object's metadata
				ObjectMetadata metadata = new ObjectMetadata();
				//Add object's metadata
				metadata.setContentType(entry.getName().substring(entry.getName().length()-3));
				metadata.setContentLength(entry.getSize());
				metadata.setUserMetadata(userData);
	
		
		// Create an input stream from the entry content
		InputStream entryInputStream = new ByteArrayInputStream(entryContent);

		            // Upload the entry's content to S3
					PutObjectRequest putRequest = new PutObjectRequest("flintstones", key, entryInputStream, metadata);
					putRequest.withCannedAcl(CannedAccessControlList.PublicRead);
					PutObjectResult result = s3.putObject(putRequest);
					System.out.println("Uploaded: " + entryName);
					System.out.println(">>>>> result: \n" + result);
		
					// Get the URL of the uploaded image
					String uploadedUrl = s3.getUrl("flintstones", key).toString();
					uploadedUrls.add(uploadedUrl);
				}
				zipInputStream.closeEntry();
			}
		
			zipInputStream.close();
		
			System.out.println(">>>>> Successfully uploaded image>>>>>\n" );

			return (Object)uploadedUrls;

	}


}


