package com.cynosure.services;

import org.springframework.stereotype.Service;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.ListBlobItem;

import net.minidev.json.JSONArray;

@Service
public class FileDownloadService {

	public static final String storageConnectionString = "DefaultEndpointsProtocol=https;" + "AccountName=cynosureblob;"
			+ "AccountKey=RlHcZdjdbPrbQJEWyzH1fqE9y9SCvQt6uCE+OVpVfxMxzAYmjtLtzqV0PV2TeE6RBpQoJJjA5QaSUVFoF/peUQ==;"
			+ "EndpointSuffix=core.windows.net";

	public String getGalleryImagesList() {
		try {
			// Retrieve storage account from connection-string.
			CloudStorageAccount storageAccount = CloudStorageAccount.parse(storageConnectionString);

			// Create the blob client.
			CloudBlobClient blobClient = storageAccount.createCloudBlobClient();

			// Retrieve reference to a previously created container.
			CloudBlobContainer container = blobClient.getContainerReference("cynosure-gallery");
			JSONArray jsonArray = new JSONArray();

			// Loop over blobs within the container and output the URI to each
			// of them.
			for (ListBlobItem blobItem : container.listBlobs()) {
				jsonArray.add(blobItem.getUri().toString());
			}
			return jsonArray.toJSONString();

		} catch (Exception e) {
			return "";
		}
	}

}
