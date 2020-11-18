/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaaspire.dvdlibrary.willitwork.dao;

import com.javaaspire.dvdlibrary.willitwork.dto.DVD;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author al_bae_ina
 */
public class DVDLibraryDaoFileImpl implements DVDLibraryDao {
    
    private Map<String, DVD> dvds = new HashMap<>();
    public static final String DVD_FILE = "dvd.txt";
    public static final String DELIMITER = "::";

    
    @Override
    public DVD addDVD(String title, DVD dvd) throws DVDLibraryDaoException {
        loadRoster();
        DVD newDvd = dvds.put(title, dvd);
        writeRoster();
        return newDvd;
    }

    
    @Override
    public List<DVD> getAllDvds() throws DVDLibraryDaoException {
        loadRoster();
        return new ArrayList<DVD>(dvds.values());
    }

    
    @Override
    public DVD getDvd(String title) throws DVDLibraryDaoException {
        loadRoster();
        return dvds.get(title);
    }

    
   
    @Override
    public DVD removeDVD(String title) throws DVDLibraryDaoException {
        loadRoster();
        DVD removedDVD = dvds.remove(title);
        writeRoster();
        return removedDVD;
    }
    
    private DVD unmarshallDVD(String dvdAsText){
        String[] dvdTokens = dvdAsText.split(DELIMITER);
        String title = dvdTokens[0];
        DVD dvdFromFile = new DVD(title);
        dvdFromFile.setReleaseDate(dvdTokens[1]);
        dvdFromFile.setMpaaRating(dvdTokens[2]);
        dvdFromFile.setDirectorName(dvdTokens[3]);
        dvdFromFile.setStudio(dvdTokens[4]);
        dvdFromFile.setUserRatingOrNote(dvdTokens[5]);
        return dvdFromFile;
    }
    
    private void loadRoster() throws DVDLibraryDaoException {
    Scanner scanner;
    
    try {
        scanner = new Scanner(new BufferedReader(new FileReader(DVD_FILE)));
        String currentLine;
        DVD currentDVD;
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentDVD = unmarshallDVD(currentLine);
            dvds.put(currentDVD.getTitle(), currentDVD);
        }
        scanner.close();
    } catch (FileNotFoundException e) {
        throw new DVDLibraryDaoException( "-_- Could not load roster data into memory.", e);
    }

    }
    
    private String marshallDVD(DVD aDvd){
        String dvdAsText = aDvd.getTitle() + DELIMITER;
        dvdAsText += aDvd.getReleaseDate()+ DELIMITER;
        dvdAsText += aDvd.getMpaaRating()+ DELIMITER;
        dvdAsText += aDvd.getDirectorName()+ DELIMITER;
        dvdAsText += aDvd.getStudio()+ DELIMITER;
        dvdAsText += aDvd.getUserRatingOrNote()+ DELIMITER;
        return dvdAsText;
    }
    
    private void writeRoster() throws DVDLibraryDaoException {
        PrintWriter out;
        try {
            out = new PrintWriter(new FileWriter(DVD_FILE));
        } catch (IOException e) {
            throw new DVDLibraryDaoException("Could not save student data.", e);
        }
        List<DVD> DVDList = new ArrayList(dvds.values());
        for (DVD currentDVD : DVDList) {
            out.println(currentDVD.getTitle() + DELIMITER
                    + currentDVD.getReleaseDate() + DELIMITER
                    + currentDVD.getMpaaRating() + DELIMITER
                    + currentDVD.getDirectorName() + DELIMITER
                    + currentDVD.getStudio() + DELIMITER
                    + currentDVD.getUserRatingOrNote());
            out.flush();
        }
        out.close();
    }
    

   
    
}
