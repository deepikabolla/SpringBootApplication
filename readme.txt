This codining excercise is developed to:

Implement a Restful API in spring-boot application. API to upload a file with a few meta-data fields. 
Persist meta-data in persistent store (In memory DB or file system and store the content on a file system)

Requirement:

Java 8
Maven

Run Command:
To build and start the server run the following command from root directory.
$ mvn spring-boot:run

Test:
After starting the server file can be uploaded from postman or other rest api tools. 
I used PostMan to test as a Client for the published web service.

Request:
http://localhost:8080/RestApiExercise/api/file/upload

Body:
file=TestFileUpload.txt

Response:

success if the file is uploaded successfully.

File uploaded is saved in the upload-dir in the project folder and the uploaded file metadata is stored in file.
