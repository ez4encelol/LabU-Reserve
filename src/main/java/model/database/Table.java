package model.database;

import java.io.File;

import model.IdGeneration;

public abstract class Table {
	protected IdGeneration idGen=IdGeneration.getInstance();
    //The read method, will take the given csv file and turn them into a map of java objects
    public abstract void loadCSV();
    //The write method, will take the current list of entities at runtime and overwrite them in the csv
    public abstract void update();
    //Method to calculate where the csv file is located
    public abstract String getDBPath();
    //load the database from the csv file
    public abstract void loadFromDB();
    //Helper method to calculate the location of all the csv files
    protected String getDBFolder(){
        // Try multiple locations in order of preference
        String[] possibleRoots = {
            System.getProperty("user.dir"),                    // Current working directory
            System.getProperty("user.dir") + File.separator + "..", // Parent directory
            "C:/Users/Danial/vscode-workspace/lab-reservation-equipment-system" // Absolute fallback
        };
        
        for (String root : possibleRoots) {
            File csvDir = new File(root + File.separator + "src" + File.separator + "resources" + File.separator + "csv" + File.separator);
            if (csvDir.exists() && csvDir.isDirectory()) {
                return csvDir.getAbsolutePath() + File.separator;
            }
        }
        // Fallback to original logic
        String projectRoot = System.getProperty("user.dir");
        return projectRoot + File.separator + "src" + File.separator + "resources" + File.separator + "csv" + File.separator;
    }
}
