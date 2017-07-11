package com.asc8.fileupload.entities;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "files")
public class FileEntry implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private String id;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;

	@Column(name = "created_by", nullable = false)
	private String createdBy;

	@Column(name = "content_type", nullable = false)
	private String contentType;

	@Column(name = "file_name", nullable = false)
	private String fileName;

	@Column(name = "location", nullable = false)
	private String location;

	@Column(name = "content_length", nullable = false)
	private Long contentLength;

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	@PrePersist
	void onCreate()
	{
		this.setId(UUID.randomUUID().toString());
		this.setCreated(new Date());
	}

	@PreUpdate
	void onUpdate()
	{
	}

	public Date getCreated()
	{
		return created;
	}

	public void setCreated(Date created)
	{
		this.created = created;
	}

	public String getCreatedBy()
	{
		return createdBy;
	}

	public void setCreatedBy(String createdBy)
	{
		this.createdBy = createdBy;
	}

	public String getContentType()
	{
		return contentType;
	}

	public void setContentType(String contentType)
	{
		this.contentType = contentType;
	}

	public String getFileName()
	{
		return fileName;
	}

	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}

	public String getLocation()
	{
		return location;
	}

	public void setLocation(String location)
	{
		this.location = location;
	}

	public Long getContentLength()
	{
		return contentLength;
	}

	public void setContentLength(Long contentLength)
	{
		this.contentLength = contentLength;
	}
}
