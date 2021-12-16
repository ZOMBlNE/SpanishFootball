import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Main
 {
     public static void main(String[] args) throws Exception
     {
         Document doc;
         int i, j, k;
         Match_info []matchInfo = new Match_info[10];
         doc = Jsoup.connect("https://www.laliga.com/en-US/laliga-santander/results/2021-22/gameweek-27").get();
         Elements element1 = doc.selectXpath("//p[@class='styled__TextRegularStyled-sc-1raci4c-0 fYuQIM']"),
                  element2 = doc.selectXpath("//p[@class='styled__TextRegularStyled-sc-1raci4c-0 hvREvZ']");
         String[] date = new String[10];
         String[] score = new String[10];
         String[] tmp2 = new String[element2.size()];
         if(element1.size()==40) k=4;
         else k=2;
         for (i=0, j=0; i<10; i++, j+=k)
         {
             date[i] = (element1.get(j).text() + " " + element1.get(j+1).text());
             if(k==4)
             score[i] = (element1.get(j+2).text() + " - " + element1.get(j+3).text());
             else score[i] = "Matches have not been played yet";
         }
         for (i=0; i<element2.size(); i++) { tmp2[i] = element2.get(i).text(); }
         for (i=0, j=0; i<10; i++, j+=2)
         {
             matchInfo[i] = new Match_info(date[i],score[i],tmp2[j],tmp2[j+1]);
         }
     }
 }