package com.epam.multithreading.reader;

import com.epam.multithreading.exception.InvalidPathException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class FileReaderTest {
    private FileReader fileReader = new FileReader();


    @Test
    public void testReadShipStrings_Strings_True() throws InvalidPathException {
        //given
        String path = "src/test/resources/Ships.txt";
        int fileStrings = 8;
        //when
        List<String> list = fileReader.readShipStrings(path);
        int resultSize = list.size();
        //then
        Assert.assertEquals(fileStrings, resultSize);
    }

    @Test(expectedExceptions = FileReader.class)
    public void testTestReadStrings_Exception() throws InvalidPathException {
        //given
        String path = "src/test/resources/qwe.txt";
        //when
        List<String> list = fileReader.readShipStrings(path);
    }
}