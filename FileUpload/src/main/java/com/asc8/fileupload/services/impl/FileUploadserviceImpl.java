package com.asc8.fileupload.services.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.asc8.fileupload.entities.FileEntry;
import com.asc8.fileupload.exceptions.ContentNotFoundException;
import com.asc8.fileupload.exceptions.EntityNotFoundException;
import com.asc8.fileupload.repositories.FileEntryRepository;
import com.asc8.fileupload.services.FileUploadService;

@Service
public class FileUploadserviceImpl implements FileUploadService
{
	@Value("${storagePath:/tmp/files}")
	private File storagePath;

	@Autowired
	private FileEntryRepository repository;

	public FileUploadserviceImpl()
	{
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void init()
	{
		storagePath.mkdirs();
	}

	@Override
	@Transactional
	public FileEntry save(String userName, String fileName, String path, String contentType, long size, byte[] bytes) throws Exception
	{
		FileEntry entity = new FileEntry();
		entity.setCreatedBy(userName);
		entity.setFileName(fileName);
		if (StringUtils.isEmpty(path)) {
			path = "/";
		}
		entity.setLocation(path);
		entity.setContentType(contentType);
		entity.setContentLength(size);
		repository.save(entity);

		File location = new File(storagePath, path);
		location.mkdirs();
		File file = new File(location, entity.getId());

		store(bytes, file);

		return entity;
	}

	private void store(byte[] bytes, File file) throws IOException
	{
		FileOutputStream out = new FileOutputStream(file);
		try {
			out.write(bytes);
		}
		finally {
			out.close();
		}
	}

	@Override
	public FileEntry retrieve(String fileId) throws EntityNotFoundException
	{
		FileEntry entity = repository.findOne(fileId);
		if (entity == null) {
			throw new EntityNotFoundException();
		}
		return entity;
	}

	@Override
	public byte[] getContent(FileEntry entity) throws Exception
	{
		File location = new File(storagePath, entity.getLocation());
		File file = new File(location, entity.getId());
		if (!file.exists() || !file.canRead()) {
			throw new ContentNotFoundException();
		}
		return readFile(file);
	}

	private byte[] readFile(File file) throws IOException
	{
		FileInputStream inp = new FileInputStream(file);
		byte[] buffer = null;
		try {
			buffer = new byte[inp.available()];
			inp.read(buffer);
		}
		finally {
			inp.close();
		}
		return buffer;
	}
}
