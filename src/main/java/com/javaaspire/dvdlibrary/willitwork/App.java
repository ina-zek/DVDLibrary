package com.javaaspire.dvdlibrary.willitwork;

import com.javaaspire.dvdlibrary.willitwork.controller.DVDLibraryController;
import com.javaaspire.dvdlibrary.willitwork.dao.DVDLibraryDao;
import com.javaaspire.dvdlibrary.willitwork.dao.DVDLibraryDaoFileImpl;
import com.javaaspire.dvdlibrary.willitwork.ui.DVDLibraryView;
import com.javaaspire.dvdlibrary.willitwork.ui.UserIO;
import com.javaaspire.dvdlibrary.willitwork.ui.UserIOConsoleImpl;




public class App{

    public static void main(String[] args) {
        UserIO myIo = new UserIOConsoleImpl();
        DVDLibraryView myView = new DVDLibraryView(myIo);
        DVDLibraryDao myDao = new DVDLibraryDaoFileImpl();
        DVDLibraryController controller = new DVDLibraryController(myDao, myView);
        controller.run();
    }


}
