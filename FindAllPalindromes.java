public class FindAllPalindromes {
  public void printAllPalindromes(String inputText){
    if(inputText==null){
      System.out.println("Input cannot be null!");
      return;
    }
    if(inputText.length()<=2){
      System.out.println("Minimum three characters should be present");
    }
    //ODD Occuring Palindromes
    int len = inputText.length();
    for(int i=1;i<len-1;i++){
      for(int j=i-1,k=i+1;j>=0&&k<len;j--,k++){
        if(inputText.charAt(j) == inputText.charAt(k)){
          System.out.println("i " + i + ", j " + j + ", k " + k + ": " + inputText.subSequence(j,k+1));
        }else{
          break;
        }
      }
    }
    /*
    //EVEN Occuring Palindromes
    for(int i=1;i<len-1;i++){
      for(int j=i,k=i+1;j>=0&&k<len;j--,k++){
        if(inputText.charAt(j) == inputText.charAt(k)){
          System.out.println("i " + i + ", j " + j + ", k " + k + ": " + inputText.subSequence(j,k+1));
        }else{
          break;
        }
      }
    }
    */
  }
  public static void main(String[] args){
    FindAllPalindromes finder = new FindAllPalindromes();
    finder.printAllPalindromes(args[0]);
  }
}
