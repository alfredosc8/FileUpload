# FileUpload
The FileUpload folder contains an eclipse project using Maven implementing simple file store microservice.
It implement a POST to store the file and GET to retrieve the content back.
Post contains 3 parameters: userName, path and file. It is getting filename, mimetype and length from the file. the metadata are stored in database and the content on filesystem.
Get is retrieving the content by ID, using the original contentType.

Tested manually with Postman.
