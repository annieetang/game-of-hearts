package starter;
// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static double averageTrust(ArrayList<Recipient> recipients){
        double trustSum = 0;
        for(Recipient temp: recipients){
            trustSum = trustSum + temp.getTrusting();
        }
        return trustSum / (recipients.size());
    }

    public static void simulateOnce(int participants, double proportionTrust, double recipientTrust, double trustIncrease,
                                    double trustDecrease, int numDates)
    {
        Random random = new Random();
        ArrayList<Initiator> initiators = new ArrayList<>();
        for(int i = 0; i < participants; i++){
            if(i < participants * proportionTrust){
                initiators.add(new Initiator(true));
            }
            else {
                initiators.add(new Initiator(false));
            }
        }

        //initialize ArrayList of recipients
        ArrayList<Recipient> recipients = new ArrayList<>();
        for(int i = 0; i < participants; i++){
            recipients.add(new Recipient(recipientTrust));
        }

        //begin simulation
        int totalDates = 0;
        int truthfulDates = 0;
        int catfishDates = 0;
        int deadweightLossDates = 0;
        int badDatesAvoided = 0;
        for(int i = 0; i < numDates; i++){
            Initiator tempInitiator = initiators.get(random.nextInt(initiators.size()));
            Recipient tempRecipient = recipients.get(random.nextInt(recipients.size()));
            //if randomly generated number is >= recipients trust level, the date proceeds
            double chance = random.nextDouble();
            if(chance <= tempRecipient.getTrusting()){
                totalDates++;
                //if the initiator is trustworthy, the date is good and the recipient becomes more trusting
                if(tempInitiator.isTrustworthy()){
                    truthfulDates++;
                    tempRecipient.updateTrusting(trustIncrease);
                }
                //if the initiator is not trustworthy, the date goes poorly and the recipient becomes less trusting
                else{
                    catfishDates++;
                    tempRecipient.updateTrusting(trustDecrease);
                }
            }
            //if the initiator is trustworthy but the recipient rejects because of low trust
            else if(chance > tempRecipient.getTrusting() && tempInitiator.isTrustworthy())
            {
                deadweightLossDates++;
            }
            //if the initiator is not trustworthy and the recipient rejects because of low t1rust
            else if(chance > tempRecipient.getTrusting() && !tempInitiator.isTrustworthy()){
                badDatesAvoided++;
            }
        }
        //end simulation
        //generate additional statistics
        double datesWentOnPercentage = ((double) totalDates / numDates)*100;
        double truthfulDatesPercentage = ((double) truthfulDates / totalDates)*100;
        double catfishDatesPercentage = ((double) catfishDates / totalDates)*100;
        double deadweightLossDatesPercentage = ((double) deadweightLossDates / numDates)*100;
        double badDatesAvoidedPercentage = ((double) badDatesAvoided / numDates)*100;

        System.out.println("Simulation completed. Statistics:");
        System.out.println("Total dates proposed: " + numDates);
        System.out.println("Total dates accepted: " + totalDates + " / " + datesWentOnPercentage + "%");
        System.out.println("Truthful dates: " + truthfulDates + " / " + truthfulDatesPercentage + "% of accepted dates");
        System.out.println("Catfish dates: " + catfishDates + " / " + catfishDatesPercentage + "% of accepted dates");
        System.out.println("Successful dates missed due to low trust: " + deadweightLossDates +
                " / " + deadweightLossDatesPercentage + "% of proposed dates");
        System.out.println("Catfish dates avoided due to low trust: " + badDatesAvoided +
                " / " + badDatesAvoidedPercentage + "% of proposed dates");
        System.out.println("Average recipient trust at end of simulation: " + averageTrust(recipients));
    }

    public static double Round(double toRound, int places)
    {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(toRound);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public static double[] simulateReturnOutput(int participants, double proportionTrust,
                                                double recipientTrust, double trustIncrease,
                                                double trustDecrease, int numDates){
        Random random = new Random();
        ArrayList<Initiator> initiators = new ArrayList<>();
        for(int i = 0; i < participants; i++){
            if(i < participants * proportionTrust){
                initiators.add(new Initiator(true));
            }
            else {
                initiators.add(new Initiator(false));
            }
        }

        //initialize ArrayList of recipients
        ArrayList<Recipient> recipients = new ArrayList<>();
        for(int i = 0; i < participants; i++){
            recipients.add(new Recipient(recipientTrust));
        }

        //begin simulation
        int totalDates = 0;
        int truthfulDates = 0;
        int catfishDates = 0;
        int deadweightLossDates = 0;
        int badDatesAvoided = 0;
        for(int i = 0; i < numDates; i++){
            Initiator tempInitiator = initiators.get(random.nextInt(initiators.size()));
            Recipient tempRecipient = recipients.get(random.nextInt(recipients.size()));
            //if randomly generated number is >= recipients trust level, the date proceeds
            double chance = random.nextDouble();
            if(chance <= tempRecipient.getTrusting()){
                totalDates++;
                //if the initiator is trustworthy, the date is good and the recipient becomes more trusting
                if(tempInitiator.isTrustworthy()){
                    truthfulDates++;
                    tempRecipient.updateTrusting(trustIncrease);
                }
                //if the initiator is not trustworthy, the date goes poorly and the recipient becomes less trusting
                else{
                    catfishDates++;
                    tempRecipient.updateTrusting(trustDecrease);
                }
            }
            //if the initiator is trustworthy but the recipient rejects because of low trust
            else if(chance > tempRecipient.getTrusting() && tempInitiator.isTrustworthy())
            {
                deadweightLossDates++;
            }
            //if the initiator is not trustworthy and the recipient rejects because of low t1rust
            else if(chance > tempRecipient.getTrusting() && !tempInitiator.isTrustworthy()){
                badDatesAvoided++;
            }
        }
        //end simulation
        //generate additional statistics
        double datesWentOnPercentage = ((double) totalDates / numDates)*100;
        double truthfulDatesPercentage = ((double) truthfulDates / totalDates)*100;
        double catfishDatesPercentage = ((double) catfishDates / totalDates)*100;
        double deadweightLossDatesPercentage = ((double) deadweightLossDates / numDates)*100;
        double badDatesAvoidedPercentage = ((double) badDatesAvoided / numDates)*100;

        //assign statistics to output array
        double[] toReturn = new double[12];
        toReturn[0] = numDates; toReturn[1] = totalDates; toReturn[2] = Round(datesWentOnPercentage,3); toReturn[3] = truthfulDates;
        toReturn[4] = Round(truthfulDatesPercentage,3); toReturn[5] = catfishDates; toReturn[6] = Round(catfishDatesPercentage,3);
        toReturn[7] = deadweightLossDates; toReturn[8] = Round(deadweightLossDatesPercentage,3);
        toReturn[9] = badDatesAvoided; toReturn[10] = Round(badDatesAvoidedPercentage,3); toReturn[11] = Round(averageTrust(recipients),4);
        return toReturn;
    }

    public static void main(String[] args) {
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose mode: '1' for single simulation or '2' for multiple simulations");
        boolean run = false;
        int mode = -1;
        while(!run){
            String input = scanner.nextLine();
            if(input.equals("1"))
            {
                System.out.println("Running single simulation mode...");
                mode = 1;
                run = true;
            }
            else if(input.equals("2")){
                System.out.println("Running multisim mode...");
                mode = 2;
                run = true;
            }
            else{
                System.out.println("Invalid input, please try again.");
            }
        }
        if(mode == 1){
            System.out.println("Enter number of initiators (number of recipients will be the same)");
            int participants = Integer.parseInt(scanner.nextLine());
            System.out.println("Enter fraction of initiators that are trustworthy as decimal 0 <= ratio <= 1");
            double proportionTrust = Double.parseDouble(scanner.nextLine());
            System.out.println("Enter initial trust level of recipients as decimal 0 <= trust level <= 1");
            double recipientTrust = Double.parseDouble(scanner.nextLine());
            System.out.println("Enter increase in recipient trust level after successful date 0 <= change <= 1");
            double trustIncrease = Double.parseDouble(scanner.nextLine());
            System.out.println("Enter decrease in recipient trust level after unsuccessful date 0 <= change <= 1");
            double trustDecrease = -1.0 * Double.parseDouble(scanner.nextLine());
            System.out.println("Enter number of interactions to simulate per participants");
            int numDates = Integer.parseInt(scanner.nextLine()) * participants;
            System.out.println("Simulating...");
            //run simulation
            simulateOnce(participants, proportionTrust, recipientTrust, trustIncrease, trustDecrease, numDates);
        }
        else if(mode == 2)
        {
            boolean varSelected = false;
            int variable = -1;
            while(!varSelected){
                System.out.println("Select the variable you would like to vary:");
                System.out.println("1: number of dates per participant");
                System.out.println("2: fraction of initiators that are trustworthy.");
                System.out.println("3: initial trust levels of recipients.");
                System.out.println("4: increase in recipient trust level after successful date.");
                System.out.println("5: decrease in recipient trust level after unsuccessful date.");
                variable = Integer.parseInt(scanner.nextLine());
                if(variable <= 5 && variable >= 1)
                {
                    varSelected = true;
                }
                else{
                    System.out.println("Invalid input, please try again.");
                }
            }

            ArrayList<double[]> outputs = new ArrayList<>();
            ArrayList<Double> inputs = new ArrayList<>();
            double lowerBound;
            double upperBound;
            double increment;

            if(variable == 1)
            {
                System.out.println("Input your lower bound for interactions per participant.");
                lowerBound = Double.parseDouble(scanner.nextLine());
                System.out.println("Input your upper bound for interactions per participant.");
                upperBound = Double.parseDouble(scanner.nextLine());
                System.out.println("Input your increment step, e.g. '25' to increase by 25 every simulation:");
                increment = Double.parseDouble(scanner.nextLine());
                System.out.println("Enter number of initiators (number of recipients will be the same)");
                int participants = Integer.parseInt(scanner.nextLine());
                System.out.println("Enter fraction of initiators that are trustworthy as decimal 0 <= ratio <= 1");
                double proportionTrust = Double.parseDouble(scanner.nextLine());
                System.out.println("Enter initial trust level of recipients as decimal 0 <= trust level <= 1");
                double recipientTrust = Double.parseDouble(scanner.nextLine());
                System.out.println("Enter increase in recipient trust level after successful date 0 <= change <= 1");
                double trustIncrease = Double.parseDouble(scanner.nextLine());
                System.out.println("Enter decrease in recipient trust level after unsuccessful date 0 <= change <= 1");
                double trustDecrease = -1.0 * Double.parseDouble(scanner.nextLine());
                lowerBound = lowerBound * participants;
                upperBound = upperBound * participants;
                increment = increment * participants;

                for(double i = lowerBound; i <= upperBound; i = i + increment){
                    inputs.add(i / participants);
                    outputs.add(simulateReturnOutput(participants, proportionTrust, recipientTrust, trustIncrease, trustDecrease, (int) i));
                }
                System.out.println("Results:");
                System.out.println("Inputs      numDates totalDates wentOn% truthfulDates (%) catfishDates(%) " +
                        "deadweightLossDates (%) badDatesAvoided (%) avgTrust");
                for(int i = 0; i < outputs.size(); i++){
                    System.out.print(inputs.get(i) + ":     ");
                    for(double j : outputs.get(i)){
                        System.out.print(j + ", ");
                    }
                    System.out.println();
                }

            }
            //varying proportion of trustworthy initiators
            if(variable == 2){
                System.out.println("Input your lower bound for initiator trustworthiness, 0 <= x < 1:");
                lowerBound = Double.parseDouble(scanner.nextLine());
                System.out.println("Input your upper bound for initiator trustworthiness, 0 < x <= 1:");
                upperBound = Double.parseDouble(scanner.nextLine());
                System.out.println("Input your increment step, e.g. '0.1' to increase by 0.1 every simulation:");
                increment = Double.parseDouble(scanner.nextLine());
                System.out.println("Enter number of initiators (number of recipients will be the same)");
                int participants = Integer.parseInt(scanner.nextLine());
                System.out.println("Enter initial trust level of recipients as decimal 0 <= trust level <= 1");
                double recipientTrust = Double.parseDouble(scanner.nextLine());
                System.out.println("Enter increase in recipient trust level after successful date 0 <= change <= 1");
                double trustIncrease = Double.parseDouble(scanner.nextLine());
                System.out.println("Enter decrease in recipient trust level after unsuccessful date 0 <= change <= 1");
                double trustDecrease = -1.0 * Double.parseDouble(scanner.nextLine());
                System.out.println("Enter number of interactions to simulate per participants");
                int numDates = Integer.parseInt(scanner.nextLine()) * participants;
                for(double i = lowerBound; i <= upperBound; i = i + increment){
                    inputs.add(i);
                    outputs.add(simulateReturnOutput(participants, i, recipientTrust, trustIncrease, trustDecrease, numDates));
                }
                for(int i = 0; i < outputs.size(); i++){
                    System.out.print(inputs.get(i) + ":    ");
                    for(double j : outputs.get(i)){
                        System.out.print(j + ", ");
                    }
                    System.out.println();
                }
            }

            // varying recipient trust level
            if(variable == 3){
                System.out.println("Input your lower bound for recipient trust level, 0 <= x < 1:");
                lowerBound = Double.parseDouble(scanner.nextLine());
                System.out.println("Input your upper bound for recipient trust level, 0 < x <= 1:");
                upperBound = Double.parseDouble(scanner.nextLine());
                System.out.println("Input your increment step, e.g. '0.1' to increase by 0.1 every simulation:");
                increment = Double.parseDouble(scanner.nextLine());
                System.out.println("Enter number of initiators (number of recipients will be the same)");
                int participants = Integer.parseInt(scanner.nextLine());
                System.out.println("Enter proportion of trustworthy individuals 0 <= x <= 1");
                double proportionTrust = Double.parseDouble(scanner.nextLine());
                System.out.println("Enter increase in recipient trust level after successful date 0 <= change <= 1");
                double trustIncrease = Double.parseDouble(scanner.nextLine());
                System.out.println("Enter decrease in recipient trust level after unsuccessful date 0 <= change <= 1");
                double trustDecrease = -1.0 * Double.parseDouble(scanner.nextLine());
                System.out.println("Enter number of interactions to simulate per participants");
                int numDates = Integer.parseInt(scanner.nextLine()) * participants;
                for(double i = lowerBound; i <= upperBound; i = i + increment){
                    inputs.add(i);
                    outputs.add(simulateReturnOutput(participants, proportionTrust, i, trustIncrease, trustDecrease, numDates));
                }
                for(int i = 0; i < outputs.size(); i++){
                    System.out.print(inputs.get(i) + ":    ");
                    for(double j : outputs.get(i)){
                        System.out.print(j + ", ");
                    }
                    System.out.println();
                }
            }

            //varying increase in recipient trust rate after successful date
            if(variable == 4){
                System.out.println("Input your lower bound for trust increase, 0 <= x < 1:");
                lowerBound = Double.parseDouble(scanner.nextLine());
                System.out.println("Input your upper bound for trust increase, 0 < x <= 1:");
                upperBound = Double.parseDouble(scanner.nextLine());
                System.out.println("Input your increment step, e.g. '0.1' to increase by 0.1 every simulation:");
                increment = Double.parseDouble(scanner.nextLine());
                System.out.println("Enter number of initiators (number of recipients will be the same)");
                int participants = Integer.parseInt(scanner.nextLine());
                System.out.println("Enter fraction of initiators that are trustworthy as decimal 0 <= ratio <= 1");
                double proportionTrust = Double.parseDouble(scanner.nextLine());
                System.out.println("Enter initial trust level of recipients as decimal 0 <= trust level <= 1");
                double recipientTrust = Double.parseDouble(scanner.nextLine());
                System.out.println("Enter decrease in recipient trust level after unsuccessful date 0 <= change <= 1");
                double trustDecrease = -1.0 * Double.parseDouble(scanner.nextLine());
                System.out.println("Enter number of interactions to simulate per participants");
                int numDates = Integer.parseInt(scanner.nextLine()) * participants;
                for(double i = lowerBound; i <= upperBound; i = i + increment){
                    inputs.add(i);
                    outputs.add(simulateReturnOutput(participants, proportionTrust, recipientTrust, i, trustDecrease, numDates));
                }
                for(int i = 0; i < outputs.size(); i++){
                    System.out.print(inputs.get(i) + ":    ");
                    for(double j : outputs.get(i)){
                        System.out.print(j + ", ");
                    }
                    System.out.println();
                }
            }

            //varying trust decrease after catfish date
            if(variable == 5){
                System.out.println("Input your lower bound for trust decrease, 0 <= x < 1:");
                lowerBound = -1.0 * Double.parseDouble(scanner.nextLine());
                System.out.println("Input your upper bound for trust decrease, 0 < x <= 1:");
                upperBound = -1.0 * Double.parseDouble(scanner.nextLine());
                System.out.println("Input your increment step, e.g. '0.1' to increase by 0.1 every simulation:");
                increment = Double.parseDouble(scanner.nextLine());
                System.out.println("Enter number of initiators (number of recipients will be the same)");
                int participants = Integer.parseInt(scanner.nextLine());
                System.out.println("Enter fraction of initiators that are trustworthy as decimal 0 <= ratio <= 1");
                double proportionTrust = Double.parseDouble(scanner.nextLine());
                System.out.println("Enter initial trust level of recipients as decimal 0 <= trust level <= 1");
                double recipientTrust = Double.parseDouble(scanner.nextLine());
                System.out.println("Enter increase in recipient trust level after successful date 0 <= change <= 1");
                double trustIncrease = Double.parseDouble(scanner.nextLine());
                System.out.println("Enter number of interactions to simulate per participants");
                int numDates = Integer.parseInt(scanner.nextLine()) * participants;
                for(double i = lowerBound; i >= upperBound; i = i - increment){
                    inputs.add(i);
                    outputs.add(simulateReturnOutput(participants, proportionTrust, recipientTrust, trustIncrease, i, numDates));
                }
                for(int i = 0; i < outputs.size(); i++){
                    System.out.print(-1.0 * inputs.get(i) + ":    ");
                    for(double j : outputs.get(i)){
                        System.out.print(j + ", ");
                    }
                    System.out.println();
                }
            }
            System.out.println("Done.");
        }
        else{
            System.out.println("Error.");
        }
    }
}