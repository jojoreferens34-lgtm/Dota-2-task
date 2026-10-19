package semester1.practice05.homework;

import java.util.Locale;

public class Task4DotaMatchStats {
    public static void main(String[] args) {
        String[] heroes = {"Pudge", "Invoker", "Juggernaut", "Crystal Maiden", "Earthshaker"};
        int[] kills = {8, 12, 10, 2, 4};
        int[] deaths = {6, 3, 2, 9, 5};
        int[] assists = {14, 9, 8, 18, 16};
        int[] netWorth = {14500, 21200, 24800, 9800, 12300};

        double[] kdaRatios = new double[5];

        int totalTeamKills = 0;
        int totalNetWorth = 0;

        for (int i = 0; i < heroes.length; i++) {
            int deathCount = Math.max(1, deaths[i]);
            kdaRatios[i] = (double) (kills[i] + assists[i]) / deathCount;

            totalTeamKills += kills[i];
            totalNetWorth += netWorth[i];
        }

        double[] killParticipation = new double[5];
        int mvpIndex = 0;

        for (int i = 0; i < heroes.length; i++) {
            killParticipation[i] =
                    (double) (kills[i] + assists[i]) / totalTeamKills * 100;

            if (kdaRatios[i] > kdaRatios[mvpIndex]) {
                mvpIndex = i;
            }
        }

        System.out.println("========================================================================");
        System.out.println("                 DOTA 2: POST-MATCH REPORT");
        System.out.println("========================================================================");
        System.out.printf("%-17s | %-10s | %-9s | %-9s | %-20s%n",
                "Hero", "K / D / A", "Net Worth", "KDA Ratio", "Kill Participation");
        System.out.println("------------------------------------------------------------------------");

        for (int i = 0; i < heroes.length; i++) {
            String kda = String.format(Locale.US, "%.2f", kdaRatios[i]);
            String kp = String.format(Locale.US, "%.2f%%", killParticipation[i]);

            System.out.printf("%-17s | %2d / %d / %2d | %,9d | %9s | %20s%n",
                    heroes[i], kills[i], deaths[i], assists[i],
                    netWorth[i], kda, kp);
        }

        System.out.println("------------------------------------------------------------------------");
        System.out.printf("Загальна кількість вбивств команди: %d%n", totalTeamKills);
        System.out.printf("Сумарний Net Worth команди:         %,d золота%n", totalNetWorth);
        System.out.printf("НАЙКРАЩИЙ ГРАВЕЦЬ МАТЧУ (MVP): %s (KDA: %.2f)%n",
                heroes[mvpIndex], kdaRatios[mvpIndex]);
        System.out.println("========================================================================");
    }
}
