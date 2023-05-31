package ibf2022.batch2.csf.backend.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import ibf2022.batch2.csf.backend.models.Bundle;
import ibf2022.batch2.csf.backend.repositories.ArchiveRepository;
import ibf2022.batch2.csf.backend.repositories.ImageRepository;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;

@Controller
@RequestMapping
@CrossOrigin(origins="*")
public class UploadController {

	// TODO: Task 2, Task 3, Task 4
	@Autowired
    private ImageRepository imageRepo;

    @Autowired
    private ArchiveRepository archiveRepo;

    @PostMapping(path="/upload", consumes= MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
    public ResponseEntity<String> postUpload(@RequestPart String name, @RequestPart String title, 
									@RequestPart String comments, @RequestPart MultipartFile myFile){
		
		System.out.println(">>>>> name:>>>>>\n" + name);
		System.out.println(">>>>> title:>>>>>\n" + title);
        System.out.println(">>>>> comments:>>>>>\n" + comments);
        System.out.println(">>>>> filename:>>>>>\n" + myFile.getOriginalFilename());
		
        List<String> urls = new ArrayList<String>();
        String bundleId = null;
		try{
            // String title, String name, String comments, List<String> urls
            urls = (List<String>) imageRepo.upload(name, title, comments, myFile);
			bundleId = (String) archiveRepo.recordBundle(title, name, comments, urls);
            System.out.println(">>>>> UploadedUrls: \n" + urls);

            JsonObject resp = Json.createObjectBuilder()
            .add("bundleId", bundleId)
            .build();
            System.out.println(">>>resp: " + resp);
    
        return ResponseEntity.ok(resp.toString());
        } catch (IOException ex){
            // ex.printStackTrace();
            String errorMessage = ex.getMessage();
            return ResponseEntity
            //status 500 is Internal server error. refer to mdn 
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .contentType(MediaType.APPLICATION_JSON)
            .body("{\"error\" : \"" + errorMessage + "\"}");
        }
            // .body("{\"error\" : \"record not found\"}");
        

    }
	

	// TODO: Task 5
    
    @GetMapping(path="/bundle/{bundleId}")
    // @GetMapping(path="/bundle")
    @ResponseBody
    public ResponseEntity<String> getBundleResponse(@RequestParam String bundleId){

        System.out.println(">>>Inside getBundleResponse>>>>>");
        JsonObject resp = null;
        List<String> urlList = new ArrayList<>();
            try{
                Bundle bundle = archiveRepo.getBundleByBundleId(bundleId);
               
                urlList = bundle.getUrls();
                JsonArrayBuilder urlListBuilder = Json.createArrayBuilder(urlList);
                resp = Json.createObjectBuilder()
                .add("bundleId", bundleId)
                .add("date", bundle.getDate())
                .add("title", bundle.getTitle())
                .add("name", bundle.getName())
                .add("comments", bundle.getComments())
                .add("urls", urlListBuilder.build())
                .build();

            } catch (IOException ex){
                return ResponseEntity.status(400)
                    .body(
                        Json.createObjectBuilder()
                            .add("error", ex.getMessage())
                            .build().toString()
                    );
            }

            System.out.println(">>>>> List of Bundles: \n" + resp.toString());
            return ResponseEntity.ok(resp.toString());

        }
	

	// TODO: Task 6

    @GetMapping("/bundles")
    @ResponseBody
    public ResponseEntity<List<Bundle>> getAllBundles() {
    List<Bundle> bundleList = archiveRepo.getBundles();
    return ResponseEntity.ok(bundleList);
}

     
    // @GetMapping(path="/bundles")
    // // @GetMapping(path="/bundle")
    // @ResponseBody
    // public ResponseEntity<String> getAllBundles(){

    //     System.out.println(">>>Inside getAllBundlesxxx>>>>>");
 
    //     List<Bundle> bundleList = archiveRepo.getBundles();
    //     System.out.println(">>>The bundleList is >>>>>" +bundleList);
    //         JsonArrayBuilder bundleListBuilder = Json.createArrayBuilder(bundleList);
            
    //         JsonArray resp = bundleListBuilder.build();
    //         System.out.println(">>>Response for getAllBundles>>>>>" +resp);

    //     return ResponseEntity.ok(resp.toString());
   

    //     // return null;

    // }

   


}
