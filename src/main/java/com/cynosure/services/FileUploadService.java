package com.cynosure.services;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.CloudBlockBlob;

@Service
public class FileUploadService {

	private static final Logger LOGGER = Logger.getLogger(FileUploadService.class);
	/*
	 * Define the connection- string with your values
	 */
	public static final String storageConnectionString = "DefaultEndpointsProtocol=https;" + "AccountName=cynosureblob;"
			+ "AccountKey=RlHcZdjdbPrbQJEWyzH1fqE9y9SCvQt6uCE+OVpVfxMxzAYmjtLtzqV0PV2TeE6RBpQoJJjA5QaSUVFoF/peUQ==;"
			+ "EndpointSuffix=core.windows.net";

	public void uploadFile(String fileName, InputStream inputStream, long fileSize) {
		// Retrieve storage account from connection-string.
		try {
			CloudStorageAccount storageAccount = CloudStorageAccount.parse(storageConnectionString);

			// Create the blob client.
			CloudBlobClient blobClient = storageAccount.createCloudBlobClient();

			// Retrieve reference to a previously created container.
			CloudBlobContainer container = blobClient.getContainerReference("cynosure-gallery");

			// Create or overwrite the "myimage.jpg" blob with contents from a
			// local file.
			CloudBlockBlob blob = container.getBlockBlobReference(fileName);
			// File source = new File(filePath);
			blob.upload(inputStream, fileSize);

		} catch (InvalidKeyException | URISyntaxException | StorageException | IOException e) {
			e.printStackTrace();
		}

		// cynosure-gallery
	}
}
