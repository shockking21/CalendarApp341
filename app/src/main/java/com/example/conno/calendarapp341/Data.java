package com.example.conno.calendarapp341;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;

public class Data{

    Context context;
    String fileName = "data.txt";
    ArrayList <Event> events = new ArrayList <>();

    public Data(Context context){
     this.context = context;
    }



    public void writeData(){

            try{
                File file = new File(fileName);

                if(file.delete())
                {
                    System.out.println("File deleted successfully");
                }
                else
                {
                    System.out.println("Failed to delete the file");
                }
                // Format for data.txt that holds event information.
                // Year, Month, dayOfMonth, StartTime,EndTime,Tag,Title,Description,Location

                String defaultEvents = "" +
                        "2018, 11, 16, 9:00, 10:00, School, Study, Study for 341 test, UBC Okanagan campus\n" +
                        "2018, 11, 18, 9:00, 10:00, School, Study, Study for 341 test, UBC Okanagan campus\n" +
                        "2018, 11, 19, 9:00, 10:00, School, Study, Study for 341 test, UBC Okanagan campus\n" +
                        "2018, 11, 21, 9:00, 10:00, School, Study, Study for 341 test, UBC Okanagan campus\n" +
                        "2018, 11, 23, 9:00, 10:00, School, Study, Study for 341 test, UBC Okanagan campus\n" +
                        "2018, 11, 25, 9:00, 10:00, School, Study, Study for 341 test, UBC Okanagan campus\n" +
                        "2018, 11, 39, 9:00, 10:00, School, Study, Study for 341 test, UBC Okanagan campus\n" +
                        "2018, 11, 30, 9:00, 10:00, School, Study, Study for 341 test, UBC Okanagan campus\n";

                //Writing to file
                FileOutputStream outputStream;
                outputStream = context.openFileOutput(fileName, Context.MODE_APPEND);
                outputStream.write(defaultEvents.getBytes());
                outputStream.close();

            }catch(Exception e){
                e.printStackTrace();
            }
    }

    public void loadEvents() {
        try {
            FileInputStream in = context.openFileInput(fileName);
            InputStreamReader inReader = new InputStreamReader(in);
            BufferedReader br = new BufferedReader(inReader);

            String line = br.readLine();

            //laoding in all information stored in the txt file into Arraylists to be used to output later

            while (line != null) {
                String[] attributes = line.split(",");

                events.add(new Event(attributes));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void addEvent(String [] a){
        //Adding events to arraylist of events
        events.add(new Event(a));
        sort();

        //Adding event to text file

        String newEvent= "";

        for(String s: a){
            newEvent += s + ",";
        }
        //removing last comma
        newEvent.substring(0, newEvent.length()-2);

        //outputting to file
        try {
            FileOutputStream outputStream;
            outputStream = context.openFileOutput(fileName, Context.MODE_APPEND);
            outputStream.write(newEvent.getBytes());
            outputStream.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void addEventToText(Event e){

    }

    public void sort(){
        Collections.sort(events);
    }

    public ArrayList <Event> search(GregorianCalendar gDate){

        ArrayList <Event> day = new ArrayList<>();

        for(Event e: events){
            if(e.getDate().compareTo(gDate) == 0){
                day.add(e);
            }
        }

        return day;
    }

    public ArrayList <Event> searchRange(GregorianCalendar gDate1, GregorianCalendar gDate2){

        ArrayList <Event> eventsRange = new ArrayList<>();

        for(Event e: events){
            if(e.getDate().compareTo(gDate1) < 1 && e.getDate().compareTo(gDate2) > -1){
                eventsRange.add(e);
            }
        }

        return eventsRange;
    }

}