/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaaspire.dvdlibrary.willitwork.dao;

import com.javaaspire.dvdlibrary.willitwork.dto.DVD;
import java.util.List;

/**
 *
 * @author al_bae_ina
 */
public interface DVDLibraryDao {
    
    DVD addDVD(String title, DVD dvd) throws DVDLibraryDaoException; 
    
    List<DVD> getAllDvds() throws DVDLibraryDaoException;
    
    DVD getDvd(String title) throws DVDLibraryDaoException;
    
    DVD removeDVD(String title) throws DVDLibraryDaoException;   

    
}
