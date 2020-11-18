/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaaspire.dvdlibrary.willitwork.controller;

import com.javaaspire.dvdlibrary.willitwork.dao.DVDLibraryDao;
import com.javaaspire.dvdlibrary.willitwork.dao.DVDLibraryDaoException;
import com.javaaspire.dvdlibrary.willitwork.dto.DVD;
import com.javaaspire.dvdlibrary.willitwork.ui.DVDLibraryView;
import java.util.List;

/**
 *
 * @author al_bae_ina
 */
public class DVDLibraryController {
    
    private DVDLibraryView view;
    private DVDLibraryDao dao;
    
    public DVDLibraryController(DVDLibraryDao dao, DVDLibraryView view) {
    this.dao = dao;
    this.view = view;
    }

    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        try {
            while (keepGoing) {
            
            menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1:
                        listDVDs();
                        break;
                    case 2:
                        createDVD();
                        break;
                    case 3:
                        viewDVD();
                        break;
                    case 4:
                        removeDVD();
                        break;
                    case 5:
                        editDVD();
                        break;
                    case 6:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }

            }
        exitMessage();
        } catch (DVDLibraryDaoException e) {
    
        view.displayErrorMessage(e.getMessage());
    
        }
    }
    

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }
    
    private void createDVD() throws DVDLibraryDaoException {
        view.displayCreateDVDBanner();
        DVD newDVD = view.getNewDVDInfo();
        dao.addDVD(newDVD.getTitle(), newDVD);
        view.displayCreateSuccessBanner();
    }
    
    private void listDVDs() throws DVDLibraryDaoException {
        view.displayDisplayAllBanner();
        List<DVD> dvdList = dao.getAllDvds();
        view.displayDVDList(dvdList);
    }
    
    private void viewDVD() throws DVDLibraryDaoException {
        view.displayDisplayDVDBanner();
        String title = view.getTitleChoice();
        DVD dvd = dao.getDvd(title);
        view.displayDVD(dvd);
    }
    
    private void removeDVD() throws DVDLibraryDaoException {
        view.displayRemoveDVDBanner();
        String title = view.getTitleChoice();
        DVD removedDVD = dao.removeDVD(title);
        view.displayRemoveResult(removedDVD);
    }
    
    private void editDVD() throws DVDLibraryDaoException {
        view.displayEditBanner();
        String title = view.getTitleChoice();
        DVD dvd = dao.getDvd(title);
    }
    
    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }   

    private void exitMessage() {
        view.displayExitBanner();
    }
    



    
}
