package com.nvne.webbackend.web;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nvne.webbackend.AutoEntrepreneurRepository;
import com.nvne.webbackend.entities.AutoEntrepreneur;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * AutoEntrepreneurRestController
 */
@CrossOrigin("*")
@RestController
public class AutoEntrepreneurRestController {

    private AutoEntrepreneurRepository autoEntrepreneurRepository;

    public AutoEntrepreneurRestController(AutoEntrepreneurRepository autoEntrepreneurRepository) {
        this.autoEntrepreneurRepository = autoEntrepreneurRepository;
    }

    @PostMapping(path="/api/autoEntrepreneurs/uploadCV", consumes = "multipart/form-data")
    @ResponseBody
    public ResponseEntity<String> uploadCV(@RequestPart("cv") MultipartFile file, @RequestPart("autoEntrepreneur") AutoEntrepreneur autoEntrepreneur ) throws IOException {
        String filename = file.getOriginalFilename()+" "+System.currentTimeMillis()+".pdf";
        Path path = Paths.get(System.getProperty("user.home")+"/upload/cv/"+filename);
        Files.write(path, file.getBytes());
        autoEntrepreneur.setCv(path.getFileName().toString());
        autoEntrepreneurRepository.save(autoEntrepreneur);
        return ResponseEntity.ok().build();
    }

    @RequestMapping("/api/autoEntrepreneurs/downloadCV/{fileName:.+}")
    public void downloadCV( HttpServletRequest request, 
                                     HttpServletResponse response, 
                                     @PathVariable("fileName") String fileName) 
    {
        //If user is not authorized - he should be thrown out from here itself
         
        //Authorized user will download the file
        // String dataDirectory = request.getServletContext().getRealPath("/WEB-INF/downloads/pdf/");
        Path file = Paths.get(System.getProperty("user.home")+"/upload/cv/"+fileName);
        if (Files.exists(file)) 
        {
            response.setContentType("application/pdf");
            response.addHeader("Content-Disposition", "attachment; filename="+fileName);
            try
            {
                Files.copy(file, response.getOutputStream());
                response.getOutputStream().flush();
            } 
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}