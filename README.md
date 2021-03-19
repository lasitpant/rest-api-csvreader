# rest-api-csvreader
Programming Language: Java Spring
Database: Mongodb
Deployment: Docker

Steps for running the application :
For version control I have used github. The source code can be cloned -
1. Git clone https://github.com/lasitpant/rest-api-csvreader.git 
2. Cd rest-api-csvreader
3. Docker-compose build, docker-compose up -d.

This will launch 2 instances , mongodb and backend service.
The rest endpoint - 
 
Copy a csv file to src/resources/input/
Post Request -  http://localhost:8080/data/process-csv
If nothing is passed in the body, then it will process all the rows of the csv.
{ “even”:”even”} or {“odd”:”odd”}




Backend Workflow:
The input folder is mounted to a folder inside the docker container from which the file gets picked.File metadata and  headers are captured and the rows are captured depending upon the parameter - even, odd or none. 


Choice for nosql database -
Since we don't have a predefined schema, we cannot construct a Pojo and hence using a sql database won’t suffice this use case. 



Structure of mongo document - 
{
 ‘uuid’ : ‘type 4 uuid for unique ness’,
‘name’ : ‘file name’
 ‘csvPayload’: ‘ArrayList’
}

The reason behind choosing this design is that it can be easily extended i.e. we can filter the csv data again and update it for corresponding uuid. Clean design instead of having a number of collections. Second point, we don’t have to think about the headers anymore. 


Core logic - 
Read a csv file using FileInputStream, having a counter to keep track of index and create a JsonArray depending on the parameter(even, odd, all) selected. Save the document to mongo.

Future enhancements - 
Using the metadata of the file mainly filename to don’t process the same file again.
Fetching the file size to limit transaction to mongo fields. Mongodb has a limit on the size of the field, we could create multiple fields for csvPayload incase of a big csv file.

References - 
Github, stackoverflow, dzone.
