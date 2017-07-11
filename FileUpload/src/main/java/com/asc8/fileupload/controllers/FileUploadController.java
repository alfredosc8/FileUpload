package com.asc8.fileupload.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.asc8.fileupload.controllers.dto.SaveResponse;
import com.asc8.fileupload.entities.FileEntry;
import com.asc8.fileupload.exceptions.MissingMandatoryException;
import com.asc8.fileupload.services.FileUploadService;

@RestController
@RequestMapping(value = "/v1/files")
public class FileUploadController
{
	  private static final Logger LOGGER = LoggerFactory.getLogger(FileUploadController.class);
	  
	  @Autowired
	  private FileUploadService service;

	    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<SaveResponse> save(@RequestParam(value = "user") String userName,
	            @RequestParam(value = "path") String path,
	            @RequestParam(value = "file") MultipartFile data)
	            throws Exception
	    {
	    	if(data == null || data.isEmpty()){
	    		throw new MissingMandatoryException();
	    	}
	    	FileEntry file = service.save(userName,data.getName(), path,data.getContentType(),data.getSize(),data.getBytes());
	    	SaveResponse result = new SaveResponse(file);
	    	result.add(ControllerLinkBuilder.linkTo(FileUploadController.class).slash(result.getFileId()).withSelfRel());
	    	return ResponseEntity.ok().body(result);
	    }
	    
	    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
	    public ResponseEntity<byte[]> retrieve(@PathVariable("id") String fileId) throws Exception
	    {
	    	FileEntry file = service.retrieve(fileId);
	        byte[] data = service.getContent(file);

	        if ( data.length == 0 )
	        {
	            return new ResponseEntity<byte[]>(HttpStatus.NO_CONTENT);
	        }

	        final HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.valueOf(file.getContentType()));
	        headers.setContentLength(file.getContentLength());
	        return new ResponseEntity<byte[]>(data, headers, HttpStatus.OK);
	    }
	    
	    
}
