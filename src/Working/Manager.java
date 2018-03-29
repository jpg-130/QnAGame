package Working;

import java.sql.ResultSet;
import java.sql.SQLException;

/**@author Jigyasa */
public class Manager {
    private static StorageManager stMan;
    private Question[] questions=new Question[500];
    private int numQuestions;
    private int level;
    private int lives;
    private int n=500;
    private Question currentQuestion;
  
    //int iMin=1,iMax=17;
    public static int getRandom(int iMin, int iMax){
        int iRand=0;
        iRand=(int)Math.round(Math.random()*(iMax-iMin))+iMin;
        return iRand;   
    }

    public Manager(String fileName) throws ClassNotFoundException, SQLException {
        stMan = new StorageManager(fileName);
        level=1;
        lives=4;
        //System.out.println("Now to populate Questions");
        populateQuestions();
        //System.out.println("Questions populated");
        
    }
    
    public int getaward(){
        n=n*2;
        return n;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }
    
    public static StorageManager getStMan() {
        return stMan;
    }
   
    public void addQuestion(String question, int questionID) throws SQLException{
        questions[numQuestions]=new Question(question, questionID);
        numQuestions++;
    }
    
    public void populateQuestions() throws SQLException{
        numQuestions=0;
        String SQL="SELECT QuestionID , Question FROM Questions WHERE Level="+level;
        //String SQL="SELECT QuestionID , Question FROM Questions WHERE Level=1";
        
        ResultSet result=stMan.query(SQL);
        while(result.next()){
            int questionID=result.getInt("QuestionID");
            String question=result.getString("Question");
            addQuestion(question, questionID);
        }
        
    }
    public void increaseLevel() throws SQLException{
        level++;
        
        populateQuestions();
    }
    public String getQuestion(){
        currentQuestion=questions[getRandom(0, numQuestions-1)];
        return currentQuestion.toString();
    }
    public boolean answer(char answer){
        if (currentQuestion.isCorect(answer)){
            level++;
            return true;
        }else{
            lives--;
            return false;
        }
        
    }
}
