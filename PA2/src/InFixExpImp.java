
public class InFixExpImp implements InFixExp {

	String expression;
	
	@Override
	public void setExp(String exp) {
		expression=exp;
	}

	@Override
	public String getExp() {
		
		return expression;
	}

	@Override
	public int getNbTokens() {
		String [] resNum=expression.split(" +");
		
		int x= resNum.length;
		
		return x;
	}
	
	String [] opps= {"*","/","%","+","-","<",">",">=","<=","==","!=","(",")"};
	private boolean checkOpp(String opp) {
		for(int j=0;j<opp.length();j++) {
			if(opps[j].equals(opp)) {
				return true;
			}
		}
		
		return false;
	}
	//helper method 1
	private int priOpp(String opp) {
		int pri=-1;
		switch(opp) {
		
		case "*" : pri=40;
		break;
		case "/" : pri=40;
			break;
		case "%" : pri=40;
			break;
		case "+" : pri=30;
		break;
		case "-" :	pri=30;
		break;
		case "<" :	pri=20;
			break;
		
		case ">" : pri=20;
		break;
		case ">=" : pri=20;
		break;
		case "<=" : pri=20;
					break;
		case "==" : pri=10;
		break;
		case "!=" : pri=10;
		break;
		default : 
			pri=-1;
		}
		return pri;
	}
	
	//helper method 2
	private int calOpp(int z,int r , String opp) {
		int u=-1;
		
		switch(opp) {
	
	case "*" : u=z*r;
	break;
	case "/" : if(r==0)
		return u;
		u=z/r;
		break;
	case "%" : if(r==0)
		return u;
		u=z%r;
		break;
	case "+" : u=z+r;
	break;
	case "-" :	u=z-r;
	
	break;
	case "<" :	if(z<r) 
				u=1;
	
				else 
					u=0;
		break;
	
	case ">" : if(z>r)
				u=1;
				else 
					u=0;
	break;
	case ">=" : if(z>=r)
		u=1;
		else 
			u=0;
	break;
	case "<=" : if(z<=r)
		u=1;
		else 
			u=0;
	break;
	case "==" : if(z==r)
		u=1;
		else 
			u=0;
	break;
	case "!=" : if(z!=r)
		u=1;
		else 
			u=0;
	}
		return u;

	}
	
	
	@Override
	public Pair<Stack<Integer>, Stack<String>> evaluate(int k) {
		Stack<String> opInf=new LinkedStack<String>();
		Stack<Integer> numInf=new LinkedStack<Integer>();
		
String [] iExpArr=expression.split(" +");
		
		
		
			for(int i=0; i<k&&i<iExpArr.length;i++) {
				char x=iExpArr[i].charAt(0);
				
				if(Character.isDigit(x)) {
					
					try {
						Integer y = Integer.parseInt(iExpArr[i]);
						numInf.push(y);
					}catch (Exception e) {
						return null;
					}
					
				}else {
					if(iExpArr[i].length()>2) {
						return null;
					}
					
					if(checkOpp(iExpArr[i])) {
						return null;
					}
					
					if(iExpArr[i].equals(")")) {
						if(opInf.empty())
							return null;
						
						String opp=opInf.pop();
						
						while(!opInf.empty()&&!opp.equals("(")) {
							if(numInf.empty()) {
								return null;
							}
								Integer r=numInf.pop();
							
						if(numInf.empty()) {
							return null;
						}
						Integer z=numInf.pop();
						
						if(r==0&&opp.equals("/")||opp.equals("%")) {
							return null;
						}
						
						Integer u =calOpp(z, r, opp);
						
						numInf.push(u);
						
						opp=opInf.pop();
						}
						
						if(opInf.empty()&&!opp.equals("(")){
							return null;
						}
					}else if(opInf.empty()||iExpArr[i].equals("(")) {
						opInf.push(iExpArr[i]);
					}else {
					
						int priNew =priOpp(iExpArr[i]);
						
						if(priNew==-1) {
							return null;
						}else {
							
							String opp=opInf.pop();
							
							int priOpp=priOpp(opp);
							
							if(priNew>priOpp) {
								opInf.push(opp);
								opInf.push(iExpArr[i]);
							}else {//checkPoint
								while(priNew<=priOpp&&!opInf.empty()&&!opp.equals("(")) {
									
									if(numInf.empty()) {
										return null;
									}
										Integer r=numInf.pop();
									
								if(numInf.empty()) {
									return null;
								}
								Integer z=numInf.pop();
								
								if(r==0&&opp.equals("/")||opp.equals("%")) {
									return null;
								}
								
								Integer u =calOpp(z, r, opp);
								
								numInf.push(u);
								
								opp=opInf.pop();
								
								}// last element
								if(numInf.empty()) {
									return null;
								}
									Integer r=numInf.pop();
								
							if(numInf.empty()) {
								return null;
							}
							Integer z=numInf.pop();
							
							if(r==0&&opp.equals("/")||opp.equals("%")) {
								return null;
							}
							
							Integer u =calOpp(z, r, opp);
							
							numInf.push(u);
							
							opp=opInf.pop();
							
							}
								
						}
					}					
					
			} 
				
			}//end for loop
			if(k>iExpArr.length) {
				
				while(!opInf.empty()) {
					String opp=opInf.pop();
					if(numInf.empty()) {
						return null;
					}
						Integer r=numInf.pop();
					
				if(numInf.empty()) {
					return null;
				}
				Integer z=numInf.pop();
				
				if(r==0&&opp.equals("/")||opp.equals("%")) {
					return null;
				}
				
				Integer u =calOpp(z, r, opp);
				
				numInf.push(u);
				
			
				
				}
				
				opInf.push("$");
			}
		
		Pair <Stack<Integer>, Stack<String>> finalRes=new Pair<Stack<Integer>, Stack<String>>(numInf,opInf);
		
		return finalRes;
	}

	@Override
	public int validate() {
		return 0;
	}

	@Override
	public PostFixExp toPostFix() {
		// TODO Auto-generated method stub
		return null;
	}

}
