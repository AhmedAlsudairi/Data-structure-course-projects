
public class PostFixExpImp implements PostFixExp {
	
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
	public Stack<Integer> evaluate(int k) {
		Stack<Integer> resStack=new LinkedStack<Integer>();
		
		String [] postArr=expression.split(" +");
		
		if(k>postArr.length) {
			return null;
		}else {
			
			for(int i=0; i<k;i++) {
				char x=postArr[i].charAt(0);
				
				if(Character.isDigit(x)) {
					
					try {
						Integer y = Integer.parseInt(postArr[i]);
						resStack.push(y);
					}catch (Exception e) {
						return null;
					}
					
				}else {
					
					if(resStack.empty()) {
						return null;
					}
						Integer r=resStack.pop();
					
				if(resStack.empty()) {
					return null;
				}
					
					Integer z=resStack.pop();
					Integer u=0;
					
					switch(postArr[i]) {
				
				case "*" : u=z*r;
				break;
				case "/" : if(r==0)
					return null;
					u=z/r;
					break;
				case "%" : if(r==0)
					return null;
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
				break;
				default : 
					return null;
				}
					
				resStack.push(u);	
			}
			}
		}
		return resStack;
	}
 
	@Override
	public int validate() {
		int resIndex=-1;
		Stack<Integer> resStack=new LinkedStack<Integer>();
		String [] postArr=expression.split(" +");
		
		
			
			for(int i=0; i<postArr.length;i++) {
				char x=postArr[i].charAt(0);
				
				if(Character.isDigit(x)) {
					
					try {
						Integer y = Integer.parseInt(postArr[i]);
						resStack.push(y);
					}catch (Exception e) {
						return i;
					}
					
				}else {
					
					if(resStack.empty()) {
						return i;
					}
						Integer r=resStack.pop();
					
				if(resStack.empty()) {
					return i;
				}
					
					Integer z=resStack.pop();
					Integer u=0;
					
					switch(postArr[i]) {
				
				case "*" : u=z*r;
				break;
				case "/" : if(r==0)
					return i;
					u=z/r;
					break;
				case "%" : if(r==0)
					return i;
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
				break;
				default : 
					return i;
				}
					
				resStack.push(u);	
			
			}
		}
			resStack.pop();
			if(!resStack.empty())
				return postArr.length;
			else
		return resIndex;
	}

	@Override
	public InFixExp toInFix() {
		InFixExp infRes=new InFixExpImp();
		if(this.validate()==-1) {
			Stack<String> resStack=new LinkedStack<String>();
			
			String [] postArr=expression.split(" +");
			
			
			
			for(int i=0; i<postArr.length;i++) {
				char x=postArr[i].charAt(0);
				
				if(Character.isDigit(x)) {
					
					try {
						Integer y = Integer.parseInt(postArr[i]);
						resStack.push(postArr[i]);
					}catch (Exception e) {
						return null;
					}
					
				}else {
					String num1= resStack.pop();
					String num2= resStack.pop();
					
					String resPostToInf;

					switch(postArr[i]) {
					
					case "*" : resPostToInf="( "+num2+" * "+num1+" )";
					break;
					case "/" : resPostToInf="( "+num2+" / "+num1+" )";
						break;
					case "%" : resPostToInf="( "+num2+" % "+num1+" )";
						break;
					case "+" : resPostToInf="( "+num2+" + "+num1+" )";
					break;
					case "-" :	resPostToInf="( "+num2+" - "+num1+" )";
					break;
					case "<" :	resPostToInf="( "+num2+" < "+num1+" )";
						break;
					
					case ">" : resPostToInf="( "+num2+" > "+num1+" )";
					break;
					case ">=" : resPostToInf="( "+num2+" >= "+num1+" )";
					break;
					case "<=" : resPostToInf="( "+num2+" <= "+num1+" )";
					break;
					case "==" : resPostToInf="( "+num2+" == "+num1+" )";
					break;
					case "!=" : resPostToInf="( "+num2+" != "+num1+" )";
					break;
					default : 
						return null;
					}
					
					resStack.push(resPostToInf);
					
					
			}
			
		}
			infRes.setExp(resStack.pop());
			return infRes;
	}else
		return null;

}
}
