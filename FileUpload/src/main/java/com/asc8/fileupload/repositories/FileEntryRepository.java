package com.asc8.fileupload.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asc8.fileupload.entities.FileEntry;

public interface FileEntryRepository extends JpaRepository<FileEntry, String>
{

}
