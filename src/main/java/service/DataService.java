/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.List;

/**
 *
 * @author franzielle
 */

public interface DataService {

     // reads all data rows from the source, skipping the header.

    List<String[]> readData(String key);

    // reads all data rows including the header.

    List<String[]> readDatawithHeader(String key);

    // overwrites the entire data source with the provided list.
 
    void writeData(String key, List<String[]> data);


    // appends a single new row to the end of the data source.

    void appendData(String key, String[] newData);

    // deletes a record based on its unique ID.

    void deleteData(String key, String id);

    // updates an existing record.
     
    boolean updateData(String key, String id, String[] updatedData);
    
}
    
