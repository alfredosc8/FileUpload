package com.asc8.fileupload.controllers.dto;

import java.io.Serializable;

import org.springframework.hateoas.ResourceSupport;

import com.asc8.fileupload.entities.FileEntry;

public class SaveResponse extends ResourceSupport implements Serializable
{

	public SaveResponse()
	{
		super();
	}

	private static final long serialVersionUID = 1L;
	private String fileId;
	private String location;
	private String name;

	public SaveResponse(FileEntry file)
	{
		super();
		
		fileId = file.getId();
		location = file.getLocation();
		name = file.getFileName();
	}

	public String getFileId()
	{
		return fileId;
	}

	public void setFileId(String id)
	{
		this.fileId = id;
	}

	public String getLocation()
	{
		return location;
	}

	public void setLocation(String location)
	{
		this.location = location;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

}
