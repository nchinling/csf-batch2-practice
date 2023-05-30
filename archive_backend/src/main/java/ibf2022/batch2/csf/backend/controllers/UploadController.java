package ibf2022.batch2.csf.backend.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import ibf2022.batch2.csf.backend.repositories.ImageRepository;

@Controller
@RequestMapping
@CrossOrigin(origins="*")
public class UploadController {

	// TODO: Task 2, Task 3, Task 4
	@Autowired
    private ImageRepository imageRepo;

    @PostMapping(path="/upload", consumes= MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
    public ResponseEntity<String> postUpload(@RequestPart String name, @RequestPart String title, 
									@RequestPart String comments, @RequestPart MultipartFile myFile){
		
		System.out.println(">>>>> name:>>>>>\n" + name);
		System.out.println(">>>>> title:>>>>>\n" + title);
        System.out.println(">>>>> comments:>>>>>\n" + comments);
        System.out.println(">>>>> filename:>>>>>\n" + myFile.getOriginalFilename());
		
		try{
            List<String> url = (List<String>) imageRepo.upload(name, title, comments, myFile);
			
        } catch (IOException ex){
            ex.printStackTrace();
        }
		return ResponseEntity.ok("{}");

    }
	

	// TODO: Task 5
	

	// TODO: Task 6

}
