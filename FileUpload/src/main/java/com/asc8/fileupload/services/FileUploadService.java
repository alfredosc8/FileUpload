package com.asc8.fileupload.services;

import com.asc8.fileupload.entities.FileEntry;
import com.asc8.fileupload.exceptions.ContentNotFoundException;
import com.asc8.fileupload.exceptions.EntityNotFoundException;

public interface FileUploadService
{

	FileEntry save(String userName, String fileName, String path, String contentType, long size, byte[] bytes) throws Exception;

	FileEntry retrieve(String fileId) throws EntityNotFoundException;

	byte[] getContent(FileEntry file) throws Exception;

}
