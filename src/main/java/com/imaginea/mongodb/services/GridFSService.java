/*
 * Copyright (c) 2011 Imaginea Technologies Private Ltd.
 * Hyderabad, India
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.imaginea.mongodb.services;

import com.imaginea.mongodb.common.exceptions.CollectionException;
import com.imaginea.mongodb.common.exceptions.DatabaseException;
import com.imaginea.mongodb.common.exceptions.DocumentException;
import com.imaginea.mongodb.common.exceptions.ValidationException;
import com.mongodb.DBObject;
import com.sun.jersey.multipart.FormDataBodyPart;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Declares service methods for performing CRUD operations on files stored in GridFS.
 *
 * @author Srinath Anantha
 */
public interface GridFSService {

    /**
     * Service handler for creating GridFS store in the specified database.
     *
     * @param dbName     Name of Database
     * @param bucketName Name of GridFS Bucket
     * @returns Status message.
     */
    public String createStore(String dbName, String bucketName) throws DatabaseException, CollectionException;

    /**
     * Service handler for getting the list of files stored in GridFS of specified database.
     *
     * @param dbName     Name of Database
     * @param bucketName Name of GridFS Bucket
     * @param query
     *@param keys
     * @param skip
     * @param limit @returns JSON representation of list of all files as a String.
     */
    public JSONObject getFileList(String dbName, String bucketName, String query, String keys, String skip, String limit) throws ValidationException, DatabaseException, CollectionException;

    /**
     * Service handler for retrieving the specified file stored in GridFS.
     *
     * @param dbName     Name of Database
     * @param bucketName Name of GridFS Bucket
     * @param id         ObjectId of the file to be retrieved
     * @returns Requested multipartfile for viewing or download based on 'download' param.
     */
    public File getFile(String dbName, String bucketName, String id) throws ValidationException, DatabaseException, CollectionException;

    /**
     * Service handler for uploading a file to GridFS.
     *
     * @param dbName      Name of Database
     * @param bucketName  Name of GridFS Bucket
     * @param formData    formDataBodyPart of the uploaded file
     * @param inputStream inputStream of the uploaded file
     * @param dbInfo      Mongo Db Configuration provided by user to connect to.
     * @returns Success message with additional file details such as name, size,
     * download url & deletion url as JSON Array string.
     */
    public JSONArray insertFile(String dbName, String bucketName, String dbInfo, InputStream inputStream, FormDataBodyPart formData) throws DatabaseException, CollectionException, DocumentException, ValidationException;

    /**
     * Service handler for dropping a file from GridFS.
     *
     * @param dbName     Name of Database
     * @param bucketName Name of GridFS Bucket
     * @param _id        Object id of file to be deleted
     * @returns Status message.
     */
    public String deleteFile(String dbName, String bucketName, String _id) throws DatabaseException, DocumentException, ValidationException, CollectionException;

    /**
     * Service handler for dropping all files from a GridFS bucket.
     *
     * @param dbName     Name of Database
     * @param bucketName Name of GridFS Bucket
     * @returns Status message.
     */
    public String dropBucket(String dbName, String bucketName) throws DatabaseException, DocumentException, ValidationException, CollectionException;

    /**
     * Service handler for getting count of all files in a GridFS bucket.
     *
     * @param dbName     Name of Database
     * @param bucketName Name of GridFS Bucket
     * @returns Status message.
     */
    public JSONObject getCount(String dbName, String bucketName) throws DatabaseException, DocumentException, ValidationException, CollectionException;
}