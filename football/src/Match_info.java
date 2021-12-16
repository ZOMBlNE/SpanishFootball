public class Match_info
{
    private String date;
    private String matchScore;
    private String firstTeam;
    private String secondTeam;

    public Match_info(String date, String matchScore, String firstTeam, String secondTeam)
    {
        this.date = date;
        this.matchScore = matchScore;
        this.firstTeam = firstTeam;
        this.secondTeam = secondTeam;
    }

    public String getDate()
    {
        return date;
    }

    public String getMatchScore()
    {
        return matchScore;
    }

    public String getFirstTeam()
    {
        return firstTeam;
    }

    public String getSecondTeam()
    {
        return secondTeam;
    }

    @Override
    public String toString() {
        return "Match_info{" +
                "date='" + date + '\'' +
                ", matchScore='" + matchScore + '\'' +
                ", firstTeam='" + firstTeam + '\'' +
                ", secondTeam='" + secondTeam + '\'' +
                '}';
    }
}
