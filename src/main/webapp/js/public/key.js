/*
公共处理JavaScript程序：按键处理
*/

/*
功能：系统快捷键处理类
调用：在客户端实现某一方法，如按下F2键显示一信息：function F2(){alert("按下了F2键！");}
*/

function KeyDownFun()
{
	this.KeyCodeValue=new Array();//快捷键keyCode值数组
	this.KeyCodeValue[0]=113;//F2键
	this.KeyCodeValue[1]=114;//F3键
	this.KeyCodeValue[2]=115;//F4键
	this.KeyCodeValue[3]=116;//F5键
	this.KeyCodeValue[4]=117;//F6键
	this.KeyCodeValue[5]=118;//F7键
	this.KeyCodeValue[6]=119;//F8键
	this.KeyCodeValue[7]=120;//F9键
	this.KeyCodeValue[8]=121;//F10键
	this.KeyCodeValue[9]=122;//F11键
	this.KeyCodeValue[10]=123;//F12键

	this.KeyCodeFun=new Array();//对应快捷键所执行的方法
	this.KeyCodeFun[0]="F2()";//按下F2键运行F2()函数
	this.KeyCodeFun[1]="F3()";//按下F3键运行F3()函数
	this.KeyCodeFun[2]="F4()";//按下F4键运行F4()函数
	this.KeyCodeFun[3]="F5()";//按下F5键运行F5()函数
	this.KeyCodeFun[4]="F6()";//按下F6键运行F6()函数
	this.KeyCodeFun[5]="F7()";//按下F7键运行F7()函数
	this.KeyCodeFun[6]="F8()";//按下F8键运行F8()函数
	this.KeyCodeFun[7]="F9()";//按下F9键运行F9()函数
	this.KeyCodeFun[8]="F10()";//按下F10键运行F10()函数
	this.KeyCodeFun[9]="F11()";//按下F11键运行F11()函数
	this.KeyCodeFun[10]="F12()";//按下F12键运行F12()函数
	
	this.NowKeyCodeValue=0;//当前按键的值
	this.NowKeyDownDo=KeyDoFun//按键执行方法
}

function KeyDoFun()//按键执行方法
{
	var i;
	for(i=0;i<this.KeyCodeValue.length;i++)
	{
		if(this.NowKeyCodeValue==this.KeyCodeValue[i])
		{
			try
			{
				eval(this.KeyCodeFun[i]);
			}
			catch(e)
			{}
			finally 
			{
				this.NowKeyCodeValue=0;
				event.keyCode=0;
				event.returnValue=false;
			}
		}
	}
}

var KeyDo=new KeyDownFun();//新建一个对象

document.onkeydown=function()
{
	KeyDo.NowKeyCodeValue=event.keyCode;//得到当前按键值
	KeyDo.NowKeyDownDo();//运行
}


/*
功能：按回车键移动焦点到下一个控件，同时可以执行某一方法
*/
function EnterKeyDo(DoFunStr)
{
	if(event.keyCode==13)//如果按下回车键
	{
		if(DoFunStr!="")//如果处理函数字符串不为空
			eval(DoFunStr);//执行该函数
		event.keyCode=9;
		event.returnValue=true;
	}
}

/*
功能：屏蔽鼠标右键
*/

document.oncontextmenu = function() { return false;} 