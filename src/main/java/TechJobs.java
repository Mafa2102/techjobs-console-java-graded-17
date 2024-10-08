import java.util.*;

/**
 * Created by LaunchCode
 */
public class TechJobs {

    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {

        // Initialize our field map with key/name pairs
        HashMap<String, String> columnChoices = new HashMap<>();
        columnChoices.put("core competency", "Skill");
        columnChoices.put("employer", "Employer");
        columnChoices.put("location", "Location");
        columnChoices.put("position type", "Position Type");
        columnChoices.put("all", "All");

        // Top-level menu options
        HashMap<String, String> actionChoices = new HashMap<>();
        actionChoices.put("search", "Search");
        actionChoices.put("list", "List");

        System.out.println("Welcome to LaunchCode's TechJobs App!");

        // Allow the user to search until they manually quit
        while (true) {

            String actionChoice = getUserSelection("View jobs by (type 'x' to quit):", actionChoices);

            if (actionChoice == null) {
                break;
            } else if (actionChoice.equals("list")) {

                String columnChoice = getUserSelection("List", columnChoices);

                if (columnChoice.equals("all")) {
                    printJobs(JobData.findAll());
                } else {

                    ArrayList<String> results = JobData.findAll(columnChoice);

                    System.out.println("\n*** All " + columnChoices.get(columnChoice) + " Values ***");

                    // Print list of skills, employers, etc
                    for (String item : results) {
                        System.out.println(item);
                    }
                }

            } else { // choice is "search"

                // How does the user want to search (e.g. by skill or employer)
                String searchField = getUserSelection("Search by:", columnChoices);

                // What is their search term?
                System.out.println("\nSearch term:");
                String searchTerm = in.nextLine();

                if (searchField.equals("all")) {
                    printJobs(JobData.findByValue(searchTerm.toLowerCase()));
                    //System.out.println("Search all fields not implemented yet.");
                } else {
                    printJobs(JobData.findByColumnAndValue(searchField, searchTerm.toLowerCase()));
                }
            }
        }
    }

    // ﻿Returns the key of the selected item from the choices Dictionary
    private static String getUserSelection(String menuHeader, HashMap<String, String> choices) {

        int choiceIdx = -1;
        Boolean validChoice = false;
        String[] choiceKeys = new String[choices.size()];

        // Put the choices in an ordered structure so we can
        // associate an integer with each one
        int i = 0;
        for (String choiceKey : choices.keySet()) {
            choiceKeys[i] = choiceKey;
            i++;
        }

        do {

            System.out.println("\n" + menuHeader);

            // Print available choices
            for (int j = 0; j < choiceKeys.length; j++) {
                System.out.println("" + j + " - " + choices.get(choiceKeys[j]));
            }

            if (in.hasNextInt()) {
                choiceIdx = in.nextInt();
                in.nextLine();
            } else {
                String line = in.nextLine();
                boolean shouldQuit = line.equals("x");
                if (shouldQuit) {
                    return null;
                }
            }

            // Validate user's input
            if (choiceIdx < 0 || choiceIdx >= choiceKeys.length) {
                System.out.println("Invalid choice. Try again.");
            } else {
                validChoice = true;
            }

        } while (!validChoice);

        return choiceKeys[choiceIdx];
    }

     //Print a list of jobs
    private static void printJobs(ArrayList<HashMap<String, String>> someJobs) {

        //System.out.println("printJobs is not implemented yet");
        //checking if there are any jobs in someJobs
        if (!someJobs.isEmpty()) {
            for (HashMap<String, String> job : someJobs) { //looping though each job
                System.out.println("\n*****");
                for(Map.Entry<String, String> data : job.entrySet()){ //iterating though each hashmap object
                    System.out.println(data.getKey() + ": " + data.getValue()); //printing each set of key-value pair
                }
                System.out.println("*****");
            }
        }
        else {
            System.out.print("No Results");
        }
    }

//    private static void printJobs(ArrayList<HashMap<String, String>> someJobs) {
//        if (!someJobs.isEmpty()) {
//            // Using a comparator to sort the jobs alphabetically by a specific field
//            someJobs.sort((job1, job2) -> job1.get("position type").compareTo(job2.get("position type")));
//
//            for (HashMap<String, String> job : someJobs) {
//                System.out.println("\n*****");
//
//                List<Map.Entry<String, String>> sortedEntries = new ArrayList<>(job.entrySet());
//                Collections.sort(sortedEntries, Comparator.comparing(Map.Entry::getKey));
//
//                LinkedHashMap<String, String> sortedMap = new LinkedHashMap<>();
//                for (Map.Entry<String, String> data : sortedEntries) {
//                    sortedMap.put(data.getKey(), data.getValue());
//                    System.out.println(sortedMap);
//                    //System.out.println(data.getKey() + ": " + data.getValue());
//                }
//                System.out.println("*****");
//            }
//        } else {
//            System.out.print("No Results");
//        }
//    }




//    private static void printJobs(ArrayList<HashMap<String, String>> someJobs) {
//        if (!someJobs.isEmpty()) {
//            // Sort the jobs alphabetically by a specific field, e.g., "position type"
//            someJobs.sort(Comparator.comparing(job -> job.get("position type")));
//
//            for (HashMap<String, String> job : someJobs) {
//                System.out.println("\n*****");
//
//                // Sort the entries of the job alphabetically by key
//                List<Map.Entry<String, String>> sortedEntries = new ArrayList<>(job.entrySet());
//                Collections.sort(sortedEntries, Comparator.comparing(Map.Entry::getKey));
//
//                // Print each key-value pair
//                for (Map.Entry<String, String> data : sortedEntries) {
//                    System.out.println(data.getKey() + ": " + data.getValue());
//                }
//                System.out.println("*****");
//            }
//        } else {
//            System.out.print("No Results");
//        }
//    }




}