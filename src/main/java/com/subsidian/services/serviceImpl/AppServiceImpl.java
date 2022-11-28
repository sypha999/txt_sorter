package com.subsidian.services.serviceImpl;

import com.subsidian.exceptions.AppException;
import com.subsidian.services.AppService;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

@Service
public class AppServiceImpl implements AppService {


    @Override
    public File sortFile(File file) throws IOException {

        //create a file to return to the user
        File sortedFile = new File("sorted_file.txt");

        //create a multi-dimensional list to hold each line of the file
        List <List<String>> my_file = new ArrayList<>();

        //scanning through usere file
        Scanner scn = new Scanner(file);

        // making sure file is valid and is a text file
        String [] a  = file.getName().split("[.]");
        try{
            if(!a[1].equalsIgnoreCase("txt"))
            throw new AppException("File is not a text file");}
        catch (IndexOutOfBoundsException e){

        }
        if(!scn.hasNextLine()){
            throw new AppException("File is empty or is an invalid text file");
        }

        while (scn.hasNextLine()){
            String [] arr; //temporary storage for each line
            arr = scn.nextLine().split(" "); //split each line
            my_file.add(Arrays.asList(arr)); //add split array to multi-dimensional array as a list
        }

        //write sorted strings to file
        try (FileWriter write = new FileWriter(sortedFile)) {
        for (List<String> f: my_file) {
                Collections.sort(f);
                write.write(String.join(" ",f)); //add a new line to the file
                write.write(System.lineSeparator()); //moves file writer to next line
            }
        }


        return sortedFile;
    }
}
