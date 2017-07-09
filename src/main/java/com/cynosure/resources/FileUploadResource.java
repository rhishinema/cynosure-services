package com.cynosure.resources;

import java.io.IOException;
import java.io.InputStream;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cynosure.services.FileUploadService;
import com.cynosure.util.BaseException;

import net.sf.json.util.JSONStringer;

@RequestMapping(value = "/upload/")
@RestController
public class FileUploadResource {

	private static final Logger LOGGER = Logger.getLogger(FileUploadResource.class);

	@Autowired
	private FileUploadService fileUploadService;

	@RequestMapping(value = "/gallery", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, method = RequestMethod.POST)
	public ResponseEntity<String> uploadFile(@RequestParam("fileData") MultipartFile bodyPart) {
		String fileName = bodyPart.getOriginalFilename();
		InputStream inputStream = null;
		InputStream uploadStreamForSize;
		try {
			inputStream = bodyPart.getInputStream();
			uploadStreamForSize = bodyPart.getInputStream();
		} catch (IOException exception) {
			throw new BaseException("Error Occurred while uploading file");
		}
		long fileSize = getSize(uploadStreamForSize);
		fileUploadService.uploadFile(fileName, inputStream, fileSize);

		String jsonResponse = new JSONStringer().object().key("response").value("success").endObject().toString();
		return new ResponseEntity<String>(jsonResponse, HttpStatus.ACCEPTED);
	}

	public static long getSize(InputStream uploadedInputStream) {
		int read = 0;
		long numOfBytes = 0;
		byte[] bytes = new byte[1024];
		try {
			while ((read = uploadedInputStream.read(bytes)) != -1) {
				numOfBytes = numOfBytes + read;
			}
		} catch (IOException e) {
			LOGGER.error("Error Occurred while calculation bytes in File");
		}
		return numOfBytes;
	}
}
