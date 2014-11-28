

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator implements ActionListener{// 1.인터페이스
 Frame frm = new Frame("전자계산기");
 Font fnt = new Font("Arial",Font.BOLD,20);
 
 Label lbl = new Label("0.0",Label.RIGHT);
 
 Panel butPnl = new Panel(); Button butTop[] = new Button[3];//버튼3개를 넣을 패널
 Panel butPnl2 = new Panel();
 Panel butPnlTot = new Panel();
 
 Button num[] = new Button[10];//숫자버튼
 Button oper[] = new Button[6];//연산자버튼
 
 //연산에 필요한 변수
 double result = 0.0;
 char operator='+';
 
 public Calculator(){}
 public void start(){
  lbl.setFont(fnt);
  frm.add(lbl, BorderLayout.NORTH);
  
  //butPnl
  butPnl.setLayout(new GridLayout(1,3));
  butPnl.add(butTop[0]=new Button("BackSpace"));
  butPnl.add(butTop[1]=new Button("Clear"));
  butPnl.add(butTop[2]=new Button("End"));
  for(int i=0; i<butTop.length; i++){//폰트셋팅
   butTop[i].setFont(fnt);
  }
  
  //butPnl2
  butPnl2.setLayout(new GridLayout(4,4));
  for(int i=7; i>=1 ; i=i-3){//행 7, 4, 1
   for(int j=i; j <= i+2; j++){// 7,8,9
    num[j] = new Button(String.valueOf(j));
    num[j].setFont(fnt);
    butPnl2.add(num[j]);
    if(j==9){
     oper[0] = new Button("+"); oper[0].setFont(fnt); butPnl2.add(oper[0]);
    }
    if(j==6){
     oper[1] = new Button("-"); oper[1].setFont(fnt); butPnl2.add(oper[1]);
    }
    if(j==3){
     oper[2] = new Button("*"); oper[2].setFont(fnt); butPnl2.add(oper[2]);
    }
   }
  }
  num[0] = new Button("0"); num[0].setFont(fnt);butPnl2.add(num[0]);
  oper[3] = new Button("."); oper[3].setFont(fnt);butPnl2.add(oper[3]);
  oper[4] = new Button("="); oper[4].setFont(fnt);butPnl2.add(oper[4]);
  oper[5] = new Button("/"); oper[5].setFont(fnt);butPnl2.add(oper[5]);
  
  butPnlTot.setLayout(new BorderLayout());
  butPnlTot.add(butPnl, BorderLayout.NORTH);
  butPnlTot.add(butPnl2, BorderLayout.CENTER);
  
  frm.add(butPnlTot, BorderLayout.CENTER);
  
  frm.setBounds(200,200,400,300);
  frm.setVisible(true);
  
  // 3.이벤트등록
  frm.addWindowListener(new WindowAdapter(){
   public void windowClosing(WindowEvent w){System.exit(0);}
  });
  //제어버튼 이벤트등록
  for(int i=0; i<butTop.length; i++){
   butTop[i].addActionListener(this);
  }
  //숫자버튼 이벤트등록
  for(int i=0; i<num.length; i++){
   num[i].addActionListener(this);
  }
  //연산자버튼 이벤트 등록
  for(int i=0; i<oper.length; i++){
   oper[i].addActionListener(this);
  }
 }
 //버튼오버라이딩
 public void actionPerformed(ActionEvent ae){
  String eventBut = ae.getActionCommand();
  char eventChar = eventBut.charAt(0);
  String lblStr = lbl.getText();//lbl의 문자열   
  switch(eventChar){
  case 'E': System.exit(0);break;//종료
  case 'B':  //Backspace
   
   String cutStr = lblStr.substring(0, lblStr.length()-1);
   lbl.setText(cutStr);
   break;
  //연산자, ., Clear
  case 'C'://Clear
   result = 0.0;
   operator = '+';
   lbl.setText("0.0");
   break;
  case '+':
  case '-':
  case '*':
  case '/':
   result = Double.parseDouble(lblStr);//String ==>숫자로 변환 이전데이타보관
   operator = eventChar;//연산자보관
   lbl.setText("");
   break;
  case '.': // .버튼 클릭시
   int point = lblStr.indexOf('.');
   if(point==-1){
    lbl.setText(lblStr+'.');
   }
   break;
  case '='://계산
   double num = Double.parseDouble(lblStr);
   switch(operator){
   case '+': result = result + num; break;
   case '-': result = result - num; break;
   case '*': result = result * num; break;
   case '/': result = result / num; break;
   //case '+': result = result + Double.parseDouble(lblStr);
   }
   lbl.setText(String.valueOf(result));
   break;
  default : //숫자버튼을 클릭했을 경우
   String lblTxt = lbl.getText();
   if(lblTxt.equals("0.0")){//레이블에 0.0이 있을때
    lbl.setText(eventBut);
   }else{//0.0이 아닌 값이 있을때
    lbl.setText(lblTxt + eventBut);
   }
  }
 } 
 
 public static void main(String[] args) {
  Calculator cal = new Calculator();
  cal.start();
 }
} 